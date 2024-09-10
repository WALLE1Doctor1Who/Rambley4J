/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package raccoon;

import files.extensions.ImageExtensions;
import geom.GeometryMath;
import icons.DebuggingIcon;
import icons.Icon2D;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.function.DoubleUnaryOperator;
import java.util.prefs.Preferences;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Milo Steier
 */
public class RambleyTestViewer extends javax.swing.JFrame {
    /**
     * This is the name of the preference node used to store the settings for 
     * this program.
     */
    private static final String PREFERENCE_NODE_NAME = 
            "milo/Rambley4J/raccoon/test";
    
    private static final String RAMBLEY_PAINTER_FLAGS_KEY = "RambleyPainterFlags";
    
    private static final String DEBUG_ELEMENTS_KEY = "DebugElements";
    
    private static final String ALWAYS_SCALE_KEY = "AlwaysScale";
    
    private static final String PRINT_LISTENERS_KEY = "PrintListeners";
    
    private static final String LINK_PAINTER_SIZE_KEY = "LinkPainterSize";
    
    private static final String PAINTER_WIDTH_KEY = "PainterWidth";
    
    private static final String PAINTER_HEIGHT_KEY = "PainterHeight";
    
    private static final String SELECTED_SAVE_FILE_KEY = "SelectedSaveFile";
    
    private static final String LINK_RAMBLEY_EYES_KEY = "LinkRambleyEyes";
    
    private static final String TEST_DOUBLE_1_KEY = "TestDouble1";
    
    private static final String TEST_DOUBLE_2_KEY = "TestDouble2";
    
    private static final String TEST_DOUBLE_3_KEY = "TestDouble3";
    
    private static final String TEST_DOUBLE_4_KEY = "TestDouble4";
    
    private static final String TEST_DOUBLE_5_KEY = "TestDouble5";
    
    private static final String TEST_DOUBLE_6_KEY = "TestDouble6";
    
    private static final String TEST_DOUBLE_7_KEY = "TestDouble7";
    
    private static final String TEST_DOUBLE_8_KEY = "TestDouble8";
    
    private static final String TEST_DOUBLE_9_KEY = "TestDouble9";
    
    private static final String TEST_DOUBLE_10_KEY = "TestDouble10";
    
    private void setSpinnerValueFromConfig(JSpinner spinner, String key, 
            double defaultValue, double mult){
        spinner.setValue(config.getDouble(key, defaultValue)*mult);
    }
    
    private void setTestDoubleFromConfig(String key, double defaultValue, double mult){
        setSpinnerValueFromConfig(getTestDoubleSpinner(key),key,defaultValue,mult);
    }
    
    private void setTestDoubleFromConfig(String key, double mult){
        setTestDoubleFromConfig(key,0,mult);
    }
    
    private void setTestDoubleFromConfig(String key){
        setTestDoubleFromConfig(key,1);
    }
    
    /**
     * Creates new form RambleyViewer
     */
    public RambleyTestViewer() {
        rambley = new RambleyTestIcon();
        debugIcon = new DebuggingIcon((Icon)rambley,false);
        initComponents();
        try{    // Try to load the settings from the preference node
            config = Preferences.userRoot().node(PREFERENCE_NODE_NAME);
            rambley.setFlags(config.getInt(RAMBLEY_PAINTER_FLAGS_KEY, 
                    rambley.getFlags()));
            debugIcon.setDebugEnabled(config.getBoolean(DEBUG_ELEMENTS_KEY, 
                    debugIcon.isDebugEnabled()));
            viewLabel.setImageAlwaysScaled(config.getBoolean(ALWAYS_SCALE_KEY, 
                    viewLabel.isImageAlwaysScaled()));
            listenerToggle.setSelected(config.getBoolean(PRINT_LISTENERS_KEY, 
                    listenerToggle.isSelected()));
            widthSpinner.setValue(config.getInt(PAINTER_WIDTH_KEY, 256));
            heightSpinner.setValue(config.getInt(PAINTER_HEIGHT_KEY, 256));
            linkSizeToggle.setSelected(config.getBoolean(LINK_PAINTER_SIZE_KEY, 
                    Objects.equals(widthSpinner.getValue(), heightSpinner.getValue())));
            bgDotSizeSpinner.setValue(config.getDouble(
                    RambleyPainter.BACKGROUND_DOT_SIZE_PROPERTY_CHANGED, 
                    rambley.getBackgroundDotSize()));
            bgDotSpacingSpinner.setValue(config.getDouble(
                    RambleyPainter.BACKGROUND_DOT_SPACING_PROPERTY_CHANGED, 
                    rambley.getBackgroundDotSpacing()));
            pGridSpinner.setValue(config.getDouble(
                    RambleyPainter.PIXEL_GRID_LINE_SPACING_PROPERTY_CHANGED, 
                    rambley.getPixelGridLineSpacing()));
            linkEyesToggle.setSelected(config.getBoolean(LINK_RAMBLEY_EYES_KEY,
                    linkEyesToggle.isSelected()));
            double eyeRX = config.getDouble(
                    RambleyPainter.RAMBLEY_RIGHT_EYE_X_PROPERTY_CHANGED, 
                    rambley.getRambleyRightEyeX());
            double eyeRY = config.getDouble(
                    RambleyPainter.RAMBLEY_RIGHT_EYE_Y_PROPERTY_CHANGED, 
                    rambley.getRambleyRightEyeY());
            double eyeLX = config.getDouble(
                    RambleyPainter.RAMBLEY_LEFT_EYE_X_PROPERTY_CHANGED, 
                    rambley.getRambleyLeftEyeX());
            double eyeLY = config.getDouble(
                    RambleyPainter.RAMBLEY_LEFT_EYE_Y_PROPERTY_CHANGED, 
                    rambley.getRambleyLeftEyeY());
            rightXSpinner.setValue(eyeRX*100);
            rightYSpinner.setValue(eyeRY*100);
            leftXSpinner.setValue(eyeLX*100);
            leftYSpinner.setValue(eyeLY*100);
            setTestDoubleFromConfig(TEST_DOUBLE_1_KEY);
            setTestDoubleFromConfig(TEST_DOUBLE_2_KEY);
            setTestDoubleFromConfig(TEST_DOUBLE_3_KEY);
            setTestDoubleFromConfig(TEST_DOUBLE_4_KEY);
            setTestDoubleFromConfig(TEST_DOUBLE_5_KEY);
            setTestDoubleFromConfig(TEST_DOUBLE_6_KEY);
            setTestDoubleFromConfig(TEST_DOUBLE_7_KEY);
            setTestDoubleFromConfig(TEST_DOUBLE_8_KEY);
            setTestDoubleFromConfig(TEST_DOUBLE_9_KEY);
            setTestDoubleFromConfig(TEST_DOUBLE_10_KEY);
            setSpinnerValueFromConfig(mouthSpinnerX,
                    RambleyPainter.RAMBLEY_OPEN_MOUTH_WIDTH_PROPERTY_CHANGED,
                    rambley.getRambleyOpenMouthWidth(),100);
            setSpinnerValueFromConfig(mouthSpinnerY,
                    RambleyPainter.RAMBLEY_OPEN_MOUTH_HEIGHT_PROPERTY_CHANGED,
                    rambley.getRambleyOpenMouthHeight(),100);
            gridThickSpinner.setValue(config.getFloat(
                    RambleyPainter.PIXEL_GRID_LINE_THICKNESS_PROPERTY_CHANGED, 
                    rambley.getPixelGridLineThickness()));
            String selFile = config.get(SELECTED_SAVE_FILE_KEY, null);
            if (selFile != null)
                fc.setSelectedFile(new File(selFile));
        } catch (SecurityException | IllegalStateException ex){
            config = null;
            System.out.println("Unable to load settings: " +ex);
        } catch (IllegalArgumentException ex){
            System.out.println("Invalid setting: " + ex);
        }
        updateSettings();
        debugToggle.setSelected(debugIcon.isDebugEnabled());
        scaleToggle.setSelected(viewLabel.isImageAlwaysScaled());
        
//        jComboBox1.setSelectedIndex(rambleyIcon.getEarTest());
//        jSpinner7.setValue(rambleyIcon.getEarSplits());
        
        viewLabel.setIcon(debugIcon);
        IconHandler handler = new IconHandler();
        rambley.addPropertyChangeListener(handler);
    }
    
    private void updateSettings(){
        backgroundToggle.setSelected(rambley.isBackgroundPainted());
        gridToggle.setSelected(rambley.isPixelGridPainted());
        evilToggle.setSelected(rambley.isRambleyEvil());
        ratioToggle.setSelected(!rambley.isAspectRatioIgnored());
        circleDotToggle.setSelected(rambley.getCircularBackgroundDots());
        shadowToggle.setSelected(rambley.isRambleyShadowPainted());
        outlineToggle.setSelected(rambley.isRambleyOutlinePainted());
        heightSpinner.setEnabled(!linkSizeToggle.isSelected());
        leftXSpinner.setEnabled(!linkEyesToggle.isSelected());
        leftYSpinner.setEnabled(!linkEyesToggle.isSelected());
        leftToggle.setSelected(rambley.isRambleyFlipped());
        showTeethToggle.setSelected(rambley.isRambleyJawClosed());
        scarfToggle.setSelected(rambley.isRambleyScarfPainted());
        glitchyToggle.setSelected(rambley.isRambleyGlitchy());
        hatToggle.setSelected(rambley.isConductorHatPainted());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fc = new javax.swing.JFileChooser();
        jSpinnerA = new javax.swing.JSpinner();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        viewLabel = new components.JThumbnailLabel();
        saveButton = new javax.swing.JButton();
        backgroundToggle = new javax.swing.JCheckBox();
        debugToggle = new javax.swing.JCheckBox();
        printButton = new javax.swing.JButton();
        gridToggle = new javax.swing.JCheckBox();
        linesToggle = new javax.swing.JCheckBox();
        scaleToggle = new javax.swing.JCheckBox();
        abTestingToggle = new javax.swing.JCheckBox();
        evilToggle = new javax.swing.JCheckBox();
        ratioToggle = new javax.swing.JCheckBox();
        listenerToggle = new javax.swing.JCheckBox();
        linkSizeToggle = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        widthSpinner = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        heightSpinner = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        jSpinner5 = new javax.swing.JSpinner();
        jSpinner6 = new javax.swing.JSpinner();
        jSpinner7 = new javax.swing.JSpinner();
        jSpinner8 = new javax.swing.JSpinner();
        jSpinner9 = new javax.swing.JSpinner();
        jSpinner10 = new javax.swing.JSpinner();
        circleDotToggle = new javax.swing.JCheckBox();
        shadowToggle = new javax.swing.JCheckBox();
        resetButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        bgDotSizeSpinner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        bgDotSpacingSpinner = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        rightXSpinner = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        rightYSpinner = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        leftXSpinner = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        leftYSpinner = new javax.swing.JSpinner();
        linkEyesToggle = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        pGridSpinner = new javax.swing.JSpinner();
        leftToggle = new javax.swing.JCheckBox();
        showTeethToggle = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        mouthSpinnerX = new javax.swing.JSpinner();
        mouthSpinnerY = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        scarfToggle = new javax.swing.JCheckBox();
        gridThickSpinner = new javax.swing.JSpinner();
        outlineToggle = new javax.swing.JCheckBox();
        glitchyToggle = new javax.swing.JCheckBox();
        hatToggle = new javax.swing.JCheckBox();

        fc.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        fc.setFileFilter(ImageExtensions.PNG_FILTER);

        jSpinnerA.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jSpinnerA.setEnabled(false);
        jSpinnerA.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerAStateChanged(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Upper", "Lower", "Tip" }));
        jComboBox1.setEnabled(false);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RambleyPainter Tester");

        viewLabel.setImageScaleMode(components.JThumbnailLabel.ALWAYS_SCALE_MAINTAIN_ASPECT_RATIO);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        backgroundToggle.setText("Background");
        backgroundToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundToggleActionPerformed(evt);
            }
        });

        debugToggle.setText("Debug");
        debugToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugToggleActionPerformed(evt);
            }
        });

        printButton.setText("Print Data");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        gridToggle.setText("Grid");
        gridToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gridToggleActionPerformed(evt);
            }
        });

        linesToggle.setText("Lines");
        linesToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linesToggleActionPerformed(evt);
            }
        });

        scaleToggle.setText("Scale");
        scaleToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scaleToggleActionPerformed(evt);
            }
        });

        abTestingToggle.setText("A-B Test");
        abTestingToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abTestingToggleActionPerformed(evt);
            }
        });

        evilToggle.setText("Evil");
        evilToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evilToggleActionPerformed(evt);
            }
        });

        ratioToggle.setText("Aspect Ratio");
        ratioToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ratioToggleActionPerformed(evt);
            }
        });

        listenerToggle.setSelected(true);
        listenerToggle.setText("Print Listeners");
        listenerToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listenerToggleActionPerformed(evt);
            }
        });

        linkSizeToggle.setText("Link Width and Height");
        linkSizeToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkSizeToggleActionPerformed(evt);
            }
        });

        jLabel1.setText("Width:");

        widthSpinner.setModel(new javax.swing.SpinnerNumberModel(256, -3, null, 1));
        widthSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                widthSpinnerStateChanged(evt);
            }
        });

        jLabel2.setText("Height:");

        heightSpinner.setModel(new javax.swing.SpinnerNumberModel(256, -3, null, 1));
        heightSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                heightSpinnerStateChanged(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(0, 2, 7, 6));

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });
        jPanel1.add(jSpinner1);

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        jSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner2StateChanged(evt);
            }
        });
        jPanel1.add(jSpinner2);

        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        jSpinner3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner3StateChanged(evt);
            }
        });
        jPanel1.add(jSpinner3);

        jSpinner4.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        jSpinner4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner4StateChanged(evt);
            }
        });
        jPanel1.add(jSpinner4);

        jSpinner5.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        jSpinner5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner5StateChanged(evt);
            }
        });
        jPanel1.add(jSpinner5);

        jSpinner6.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        jSpinner6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner6StateChanged(evt);
            }
        });
        jPanel1.add(jSpinner6);

        jSpinner7.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        jSpinner7.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner7StateChanged(evt);
            }
        });
        jPanel1.add(jSpinner7);

        jSpinner8.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        jSpinner8.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner8StateChanged(evt);
            }
        });
        jPanel1.add(jSpinner8);

        jSpinner9.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        jSpinner9.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner9StateChanged(evt);
            }
        });
        jPanel1.add(jSpinner9);

        jSpinner10.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        jSpinner10.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner10StateChanged(evt);
            }
        });
        jPanel1.add(jSpinner10);

        circleDotToggle.setText("Circle Dots");
        circleDotToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                circleDotToggleActionPerformed(evt);
            }
        });

        shadowToggle.setText("Shadow");
        shadowToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shadowToggleActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("BG Dot Size:");

        bgDotSizeSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));
        bgDotSizeSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bgDotSizeSpinnerStateChanged(evt);
            }
        });

        jLabel4.setText("BG Dot Spacing:");

        bgDotSpacingSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));
        bgDotSpacingSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bgDotSpacingSpinnerStateChanged(evt);
            }
        });

        jLabel5.setText("Right X:");

        rightXSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, -3.0d, 103.0d, 1.0d));
        rightXSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rightEyeSpinnerStateChanged(evt);
            }
        });

        jLabel6.setText("Right Y:");

        rightYSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, -3.0d, 103.0d, 1.0d));
        rightYSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rightEyeSpinnerStateChanged(evt);
            }
        });

        jLabel7.setText("Left X:");

        leftXSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, -3.0d, 103.0d, 1.0d));
        leftXSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                leftEyeSpinnerStateChanged(evt);
            }
        });

        jLabel8.setText("Left Y:");

        leftYSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, -3.0d, 103.0d, 1.0d));
        leftYSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                leftEyeSpinnerStateChanged(evt);
            }
        });

        linkEyesToggle.setSelected(true);
        linkEyesToggle.setText("Link Eyes");
        linkEyesToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkEyesToggleActionPerformed(evt);
            }
        });

        jLabel9.setText("Pixel Grid:");

        pGridSpinner.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));
        pGridSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pGridSpinnerStateChanged(evt);
            }
        });

        leftToggle.setText("Flipped");
        leftToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftToggleActionPerformed(evt);
            }
        });

        showTeethToggle.setText("Close Jaw");
        showTeethToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTeethToggleActionPerformed(evt);
            }
        });

        jLabel10.setText("Mouth Wide:");

        mouthSpinnerX.setModel(new javax.swing.SpinnerNumberModel(0.0d, -3.0d, 103.0d, 1.0d));
        mouthSpinnerX.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mouthSpinnerXStateChanged(evt);
            }
        });

        mouthSpinnerY.setModel(new javax.swing.SpinnerNumberModel(0.0d, -3.0d, 103.0d, 1.0d));
        mouthSpinnerY.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mouthSpinnerYStateChanged(evt);
            }
        });

        jLabel11.setText("Mouth Height:");

        scarfToggle.setText("Scarf");
        scarfToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scarfToggleActionPerformed(evt);
            }
        });

        gridThickSpinner.setModel(new javax.swing.SpinnerNumberModel(1.0f, -3.0f, null, 1.0f));
        gridThickSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                gridThickSpinnerStateChanged(evt);
            }
        });

        outlineToggle.setText("Outline");
        outlineToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outlineToggleActionPerformed(evt);
            }
        });

        glitchyToggle.setText("Glitchy");
        glitchyToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                glitchyToggleActionPerformed(evt);
            }
        });

        hatToggle.setText("Conductor Hat");
        hatToggle.setEnabled(false);
        hatToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hatToggleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(widthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(heightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(linkSizeToggle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(abTestingToggle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(linesToggle))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(debugToggle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(printButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveButton)
                                .addGap(8, 8, 8)
                                .addComponent(resetButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scaleToggle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(listenerToggle))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mouthSpinnerX, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mouthSpinnerY, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(showTeethToggle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(leftToggle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(glitchyToggle))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(backgroundToggle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(gridToggle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(evilToggle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ratioToggle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(circleDotToggle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(shadowToggle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(outlineToggle))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bgDotSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bgDotSpacingSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pGridSpinner))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rightXSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rightYSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(leftXSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(leftYSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(linkEyesToggle))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(gridThickSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(scarfToggle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hatToggle)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(backgroundToggle)
                            .addComponent(gridToggle)
                            .addComponent(evilToggle)
                            .addComponent(ratioToggle)
                            .addComponent(circleDotToggle)
                            .addComponent(shadowToggle)
                            .addComponent(outlineToggle))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(bgDotSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(bgDotSpacingSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(pGridSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gridThickSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rightXSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(rightYSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(leftXSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(leftYSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linkEyesToggle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(mouthSpinnerX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mouthSpinnerY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(leftToggle)
                            .addComponent(showTeethToggle)
                            .addComponent(glitchyToggle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(scarfToggle)
                            .addComponent(hatToggle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(debugToggle)
                            .addComponent(saveButton)
                            .addComponent(printButton)
                            .addComponent(scaleToggle)
                            .addComponent(listenerToggle)
                            .addComponent(resetButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(widthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(heightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(linkSizeToggle)
                            .addComponent(abTestingToggle)
                            .addComponent(linesToggle)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        System.out.println("Rambley Icon: " + rambley);
//        System.out.println("Background Gradient: "+rambleyIcon.getBackgroundGradient());
        System.out.println("Test Double 1: " + rambley.getTestDouble1());
        System.out.println("Test Double 2: " + rambley.getTestDouble2());
        System.out.println("Test Double 3: " + rambley.getTestDouble3());
        System.out.println("Test Double 4: " + rambley.getTestDouble4());
        System.out.println("Test Double 5: " + rambley.getTestDouble5());
        System.out.println("Test Double 6: " + rambley.getTestDouble6());
    }//GEN-LAST:event_printButtonActionPerformed

    private void debugToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugToggleActionPerformed
        debugIcon.setDebugEnabled(debugToggle.isSelected());
        viewLabel.repaint();
        if (config != null)
            config.putBoolean(DEBUG_ELEMENTS_KEY, debugToggle.isSelected());
    }//GEN-LAST:event_debugToggleActionPerformed
    
    private void updateConfigFlags(){
        if (config != null)
            config.putInt(RAMBLEY_PAINTER_FLAGS_KEY, rambley.getFlags());
    }
    
    private void backgroundToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backgroundToggleActionPerformed
        rambley.setBackgroundPainted(backgroundToggle.isSelected());
    }//GEN-LAST:event_backgroundToggleActionPerformed

    private void gridToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gridToggleActionPerformed
        rambley.setPixelGridPainted(gridToggle.isSelected());
    }//GEN-LAST:event_gridToggleActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        File file = null;
        while (file == null && fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
            file = fc.getSelectedFile();
            if (file != null){
                if (ImageExtensions.PNG_FILTER.equals(fc.getFileFilter()) && 
                        !ImageExtensions.PNG_FILTER.accept(file)){
                    file = new File(file.toString()+"."+ImageExtensions.PNG);
                    fc.setSelectedFile(file);
                }
                if (file.exists()){
                    int option = JOptionPane.showConfirmDialog(this, 
                            "A file named \""+file.getName()+"\" already exists.\n"
                                    + "Would you like to replace it?", 
                            "File Already Exists", JOptionPane.YES_NO_CANCEL_OPTION, 
                            JOptionPane.WARNING_MESSAGE);
                    switch(option){
                        case(JOptionPane.NO_OPTION):
                            file = null;
                        case(JOptionPane.YES_OPTION):
                            break;
                        default:
                            return;
                    }
                }
            }
        }
        
        if (file != null){
            if (config != null)
                config.put(SELECTED_SAVE_FILE_KEY, file.toString());
            BufferedImage image = debugIcon.toImage(viewLabel);
            try {
                ImageIO.write(image, "png", file);
            } catch (IOException ex) {
                System.out.println("Failed to save image: " + ex);
//                Logger.getLogger(JThumbnailLabelTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void linesToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linesToggleActionPerformed
        viewLabel.repaint();
    }//GEN-LAST:event_linesToggleActionPerformed

    private void scaleToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scaleToggleActionPerformed
        viewLabel.setImageAlwaysScaled(scaleToggle.isSelected());
        if (config != null)
            config.putBoolean(ALWAYS_SCALE_KEY, viewLabel.isImageAlwaysScaled());
    }//GEN-LAST:event_scaleToggleActionPerformed

    private void abTestingToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abTestingToggleActionPerformed
        viewLabel.repaint();
    }//GEN-LAST:event_abTestingToggleActionPerformed

    private void evilToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evilToggleActionPerformed
        rambley.setRambleyEvil(evilToggle.isSelected());
    }//GEN-LAST:event_evilToggleActionPerformed

    private void ratioToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ratioToggleActionPerformed
        rambley.setAspectRatioIgnored(!ratioToggle.isSelected());
    }//GEN-LAST:event_ratioToggleActionPerformed
    
    private JSpinner getTestDoubleSpinner(String key){
        switch(key){
            case(TEST_DOUBLE_1_KEY):
                return jSpinner1;
            case(TEST_DOUBLE_2_KEY):
                return jSpinner2;
            case(TEST_DOUBLE_3_KEY):
                return jSpinner3;
            case(TEST_DOUBLE_4_KEY):
                return jSpinner4;
            case(TEST_DOUBLE_5_KEY):
                return jSpinner5;
            case(TEST_DOUBLE_6_KEY):
                return jSpinner6;
            case(TEST_DOUBLE_7_KEY):
                return jSpinner7;
            case(TEST_DOUBLE_8_KEY):
                return jSpinner8;
            case(TEST_DOUBLE_9_KEY):
                return jSpinner9;
            case(TEST_DOUBLE_10_KEY):
                return jSpinner10;
        }
        return null;
    }
    
    private double getTestDouble(String key){
        switch(key){
            case(TEST_DOUBLE_1_KEY):
                return rambley.getTestDouble1();
            case(TEST_DOUBLE_2_KEY):
                return rambley.getTestDouble2();
            case(TEST_DOUBLE_3_KEY):
                return rambley.getTestDouble3();
            case(TEST_DOUBLE_4_KEY):
                return rambley.getTestDouble4();
            case(TEST_DOUBLE_5_KEY):
                return rambley.getTestDouble5();
            case(TEST_DOUBLE_6_KEY):
                return rambley.getTestDouble6();
            case(TEST_DOUBLE_7_KEY):
                return rambley.getTestDouble7();
            case(TEST_DOUBLE_8_KEY):
                return rambley.getTestDouble8();
            case(TEST_DOUBLE_9_KEY):
                return rambley.getTestDouble9();
            case(TEST_DOUBLE_10_KEY):
                return rambley.getTestDouble10();
        }
        return 0;
    }
    
    private void setTestDoubleInConfig(String key){
        viewLabel.repaint();
        if (config == null)
            return;
        config.putDouble(key,getTestDouble(key));
    }
    
    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        setTestDoubleInConfig(TEST_DOUBLE_1_KEY);
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
        setTestDoubleInConfig(TEST_DOUBLE_2_KEY);
    }//GEN-LAST:event_jSpinner2StateChanged

    private void jSpinner3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner3StateChanged
        setTestDoubleInConfig(TEST_DOUBLE_3_KEY);
    }//GEN-LAST:event_jSpinner3StateChanged

    private void jSpinner4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner4StateChanged
        setTestDoubleInConfig(TEST_DOUBLE_4_KEY);
    }//GEN-LAST:event_jSpinner4StateChanged

    private void jSpinner5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner5StateChanged
        setTestDoubleInConfig(TEST_DOUBLE_5_KEY);
    }//GEN-LAST:event_jSpinner5StateChanged

    private void jSpinner6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner6StateChanged
        setTestDoubleInConfig(TEST_DOUBLE_6_KEY);
    }//GEN-LAST:event_jSpinner6StateChanged

    private void jSpinnerAStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerAStateChanged
//        rambleyIcon.setEarSplits((Integer)jSpinner7.getValue());
    }//GEN-LAST:event_jSpinnerAStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //        double earX = 0;
        //        double earY = 0;
        //        ArrayList<Point2D> upperPts = new ArrayList<>();
        //        ArrayList<Point2D> lowerPts = new ArrayList<>();
        //        ArrayList<Point2D> tipPts = new ArrayList<>();
        //        ArrayList<Point2D> ctrlPts1U = new ArrayList<>();
        //        ArrayList<Point2D> ctrlPts2U = new ArrayList<>();
        //        ArrayList<Point2D> ctrlPts1L = new ArrayList<>();
        //        ArrayList<Point2D> ctrlPts2L = new ArrayList<>();
        //        ArrayList<Point2D> ctrlPts1T = new ArrayList<>();
        //        ArrayList<Point2D> ctrlPts2T = new ArrayList<>();
        //        DoubleUnaryOperator upperX = rambleyIcon.getRambleyEarX(0);
        //        DoubleUnaryOperator upperY = rambleyIcon.getRambleyEarY(0);
        //        DoubleUnaryOperator lowerX = rambleyIcon.getRambleyEarX(1);
        //        DoubleUnaryOperator lowerY = rambleyIcon.getRambleyEarY(1);
        //        DoubleUnaryOperator tipX = rambleyIcon.getRambleyEarX(2);
        //        DoubleUnaryOperator tipY = rambleyIcon.getRambleyEarY(2);
        //        rambleyIcon.calculateSplinePoints(earX, earY, 4, upperX, upperY,
            //                upperPts, ctrlPts1U, ctrlPts2U);
        //        rambleyIcon.calculateSplinePoints(earX, earY, 4, lowerX, lowerY,
            //                lowerPts, ctrlPts1L, ctrlPts2L);
        //        rambleyIcon.calculateSplinePoints(earX, earY, 7, tipX, tipY,
            //                tipPts, ctrlPts1T, ctrlPts2T);
        //        System.out.println(upperPts);
        //        System.out.println(lowerPts);
        //        System.out.println(tipPts);
        //        Line2D upper1 = new Line2D.Double(upperPts.get(0),upperPts.get(1));
        //        int tipOff = 0;
        //        Line2D tip1 = new Line2D.Double(tipPts.get(tipOff+1),tipPts.get(tipOff));
        //        while (!upper1.intersectsLine(tip1)){
            //            tipOff ++;
            //            tip1.setLine(tipPts.get(tipOff+1),tipPts.get(tipOff));
            //        }
        //        System.out.printf("Upper To Tip?: (%12.8f, %12.8f)%n",earX+15.5,earY+upperY.applyAsDouble(15.5));
        //
        //        System.out.printf("((%9.5f, %9.5f), (%9.5f, %9.5f)), ((%9.5f, %9.5f), (%9.5f, %9.5f))%n",
            //                upper1.getX1(),upper1.getY1(),upper1.getX2(),upper1.getY2(),
            //                tip1.getX1(),tip1.getY1(),tip1.getX2(),tip1.getY2());
        //
        ////        for (int i = 0; i < 25; i++){
            ////            if (upper1.getP1().distance(upper1.getP2()) <= tip1.getP1().distance(tip1.getP2())){
                ////                getIntersectingLine(tip1,upper1,earX,earY,tipY);
                ////            } else {
                ////                getIntersectingLine(upper1,tip1,earX,earY,upperY);
                ////            }
            ////        }
        ////        System.out.printf("((%9.5f, %9.5f), (%9.5f, %9.5f)), ((%9.5f, %9.5f), (%9.5f, %9.5f))%n",
            ////                upper1.getX1(),upper1.getY1(),upper1.getX2(),upper1.getY2(),
            ////                tip1.getX1(),tip1.getY1(),tip1.getX2(),tip1.getY2());
        ////        double tempX = (upper1.getX1()+upper1.getX2()+tip1.getX1()+tip1.getX2())/4.0;
        ////        double tempY = (upperY.applyAsDouble(tempX-earX)+tipY.applyAsDouble(tempX-earX))/2.0+earY;
        ////        System.out.printf("Upper To Tip:  (%9.5f, %9.5f)%n",tempX,tempY);
        //
        //        Point2D uTT = getLineIntersection(earX, earY, tip1, upper1, tipY, upperY);
        //        System.out.printf("Upper To Tip:  (%12.8f, %12.8f)%n",uTT.getX(),uTT.getY());
        //        uTT = rambleyIcon.getRambleyEarUpperTip(earX, earY, null);
        //        System.out.printf("Upper To Tip:  (%12.8f, %12.8f)%n",uTT.getX(),uTT.getY());
        //
        //        tipOff = 0;
        //        Line2D tip2 = new Line2D.Double();
        //        Line2D lower1 = new Line2D.Double();
        //        int lowerOff = 0;
        //        do{
            //            tip2.setLine(tipPts.get(tipPts.size()-2-tipOff),tipPts.get(tipPts.size()-1-tipOff));
            //            lower1.setLine(lowerPts.get(0),lowerPts.get(1));
            //            for (int off = 0; off < lowerPts.size()-1 && !tip2.intersectsLine(lower1); off++){
                //                lower1.setLine(lowerPts.get(off+0),lowerPts.get(off+1));
                //                lowerOff = off;
                //            }
            //            tipOff++;
            //        }
        //        while (!tip2.intersectsLine(lower1));
        //
        //        System.out.printf("((%9.5f, %9.5f), (%9.5f, %9.5f)), ((%9.5f, %9.5f), (%9.5f, %9.5f)) %2d %2d%n",
            //                lower1.getX1(),lower1.getY1(),lower1.getX2(),lower1.getY2(),
            //                tip2.getX1(),tip2.getY1(),tip2.getX2(),tip2.getY2(), tipOff-1, lowerOff);
        //
        ////        for (int i = 0; i < 25; i++){
            ////            if (lower1.getP1().distance(lower1.getP2()) <= tip2.getP1().distance(tip2.getP2())){
                ////                getIntersectingLine(tip2,lower1,earX,earY,tipY);
                ////            } else {
                ////                getIntersectingLine(lower1,tip2,earX,earY,lowerY);
                ////            }
            ////        }
        ////
        ////        double tempX1 = (lower1.getX1()+lower1.getX2()+tip2.getX1()+tip2.getX2())/4.0;
        ////        double tempY1 = (lowerY.applyAsDouble(tempX1-earX)+tipY.applyAsDouble(tempX1-earX))/2.0+earY;
        ////        System.out.printf("Lower To Tip:  (%9.5f, %9.5f)%n",tempX1,tempY1);
        //
        //        Point2D lTT = getLineIntersection(earX, earY, tip2, lower1, tipY, lowerY);
        //        System.out.printf("Lower To Tip:  (%12.8f, %12.8f)%n",lTT.getX(),lTT.getY());
        //        lTT = rambleyIcon.getRambleyEarLowerTip(earX, earY, null);
        //        System.out.printf("Lower To Tip:  (%12.8f, %12.8f)%n",lTT.getX(),lTT.getY());

        //        System.out.printf("((%9.5f, %9.5f), (%9.5f, %9.5f)), ((%9.5f, %9.5f), (%9.5f, %9.5f))%n",
            //                lower1.getX1(),lower1.getY1(),lower1.getX2(),lower1.getY2(),
            //                tip2.getX1(),tip2.getY1(),tip2.getX2(),tip2.getY2());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
//        rambleyIcon.setEarTest(jComboBox1.getSelectedIndex());
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void widthSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_widthSpinnerStateChanged
        if (linkSizeToggle.isSelected())
            heightSpinner.setValue(widthSpinner.getValue());
        viewLabel.repaint();
        if (config != null)
            config.putInt(PAINTER_WIDTH_KEY, (int)widthSpinner.getValue());
    }//GEN-LAST:event_widthSpinnerStateChanged

    private void heightSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_heightSpinnerStateChanged
        if (!linkSizeToggle.isSelected())
            viewLabel.repaint();
        if (config != null)
            config.putInt(PAINTER_HEIGHT_KEY, (int)heightSpinner.getValue());
    }//GEN-LAST:event_heightSpinnerStateChanged

    private void linkSizeToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkSizeToggleActionPerformed
        heightSpinner.setEnabled(!linkSizeToggle.isSelected());
        if (linkSizeToggle.isSelected())
            heightSpinner.setValue(widthSpinner.getValue());
        viewLabel.repaint();
        if (config != null)
            config.putBoolean(LINK_PAINTER_SIZE_KEY, linkSizeToggle.isSelected());
    }//GEN-LAST:event_linkSizeToggleActionPerformed

    private void listenerToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listenerToggleActionPerformed
        if (config != null)
            config.putBoolean(PRINT_LISTENERS_KEY, listenerToggle.isSelected());
    }//GEN-LAST:event_listenerToggleActionPerformed

    private void circleDotToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_circleDotToggleActionPerformed
        rambley.setCircularBackgroundDots(circleDotToggle.isSelected());
    }//GEN-LAST:event_circleDotToggleActionPerformed

    private void shadowToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shadowToggleActionPerformed
        rambley.setRambleyShadowPainted(shadowToggle.isSelected());
    }//GEN-LAST:event_shadowToggleActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        rambley.setFlags(RambleyPainter.PAINT_BACKGROUND_FLAG | 
            RambleyPainter.PAINT_PIXEL_GRID_FLAG | 
                RambleyPainter.PAINT_RAMBLEY_OUTLINE_FLAG | 
                RambleyPainter.PAINT_RAMBLEY_SHADOW_FLAG | 
                RambleyPainter.PAINT_RAMBLEY_SCARF_FLAG);
        widthSpinner.setValue(256);
        heightSpinner.setValue(256);
        linkSizeToggle.setSelected(Objects.equals(widthSpinner.getValue(), 
                heightSpinner.getValue()));
        bgDotSizeSpinner.setValue(RambleyPainter.DEFAULT_BACKGROUND_DOT_SIZE);
        bgDotSpacingSpinner.setValue(RambleyPainter.DEFAULT_BACKGROUND_DOT_SPACING);
        pGridSpinner.setValue(RambleyPainter.DEFAULT_PIXEL_GRID_LINE_SPACING);
        linkEyesToggle.setSelected(true);
        rightXSpinner.setValue(50.0);
        rightYSpinner.setValue(50.0);
        leftXSpinner.setValue(rightXSpinner.getValue());
        leftYSpinner.setValue(rightYSpinner.getValue());
        mouthSpinnerX.setValue(100.0);
        mouthSpinnerY.setValue(0.0);
        updateConfigFlags();
        updateSettings();
        if (config != null){
            config.putBoolean(LINK_PAINTER_SIZE_KEY, linkSizeToggle.isSelected());
            config.putBoolean(LINK_RAMBLEY_EYES_KEY, linkEyesToggle.isSelected());
        }
    }//GEN-LAST:event_resetButtonActionPerformed

    private void bgDotSizeSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bgDotSizeSpinnerStateChanged
        rambley.setBackgroundDotSize((Double)bgDotSizeSpinner.getValue());
    }//GEN-LAST:event_bgDotSizeSpinnerStateChanged

    private void bgDotSpacingSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bgDotSpacingSpinnerStateChanged
        rambley.setBackgroundDotSpacing((Double)bgDotSpacingSpinner.getValue());
    }//GEN-LAST:event_bgDotSpacingSpinnerStateChanged

    private void rightEyeSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rightEyeSpinnerStateChanged
        if (linkEyesToggle.isSelected()){
            rambley.setRambleyEyes((double)rightXSpinner.getValue()/100, 
                    (double)rightYSpinner.getValue()/100);
        } else {
            rambley.setRambleyRightEye((double)rightXSpinner.getValue()/100, 
                    (double)rightYSpinner.getValue()/100);
        }
//        if (config != null){
//            config.putDouble(RAMBLEY_RIGHT_EYE_X_KEY, rambleyIcon.getRambleyRightEyeX());
//            config.putDouble(RAMBLEY_RIGHT_EYE_Y_KEY, rambleyIcon.getRambleyRightEyeY());
//            if (linkEyesToggle.isSelected()){
//                config.putDouble(RAMBLEY_LEFT_EYE_X_KEY, rambleyIcon.getRambleyLeftEyeX());
//                config.putDouble(RAMBLEY_LEFT_EYE_Y_KEY, rambleyIcon.getRambleyLeftEyeY());
//            }
//        }
    }//GEN-LAST:event_rightEyeSpinnerStateChanged

    private void leftEyeSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_leftEyeSpinnerStateChanged
        if (linkEyesToggle.isSelected())
            return;
        rambley.setRambleyLeftEye((double)leftXSpinner.getValue()/100, 
                (double)leftYSpinner.getValue()/100);
//        if (config != null){
//            config.putDouble(RAMBLEY_LEFT_EYE_X_KEY, rambleyIcon.getRambleyLeftEyeX());
//            config.putDouble(RAMBLEY_LEFT_EYE_Y_KEY, rambleyIcon.getRambleyLeftEyeY());
//        }
    }//GEN-LAST:event_leftEyeSpinnerStateChanged

    private void linkEyesToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkEyesToggleActionPerformed
        leftXSpinner.setEnabled(!linkEyesToggle.isSelected());
        leftYSpinner.setEnabled(!linkEyesToggle.isSelected());
        if (linkEyesToggle.isSelected()){
            rambley.setRambleyEyes((double)rightXSpinner.getValue()/100, 
                    (double)rightYSpinner.getValue()/100);
        } else {
            rambley.setRambleyRightEye((double)rightXSpinner.getValue()/100, 
                    (double)rightYSpinner.getValue()/100);
            rambley.setRambleyLeftEye((double)leftXSpinner.getValue()/100, 
                (double)leftYSpinner.getValue()/100);
        }
        if (config != null){
//            config.putDouble(RAMBLEY_RIGHT_EYE_X_KEY, rambleyIcon.getRambleyRightEyeX());
//            config.putDouble(RAMBLEY_RIGHT_EYE_Y_KEY, rambleyIcon.getRambleyRightEyeY());
//            config.putDouble(RAMBLEY_LEFT_EYE_X_KEY, rambleyIcon.getRambleyLeftEyeX());
//            config.putDouble(RAMBLEY_LEFT_EYE_Y_KEY, rambleyIcon.getRambleyLeftEyeY());
            config.putBoolean(LINK_RAMBLEY_EYES_KEY, linkEyesToggle.isSelected());
        }
    }//GEN-LAST:event_linkEyesToggleActionPerformed

    private void pGridSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pGridSpinnerStateChanged
        rambley.setPixelGridLineSpacing((Double)pGridSpinner.getValue());
    }//GEN-LAST:event_pGridSpinnerStateChanged

    private void leftToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftToggleActionPerformed
        rambley.setRambleyFlipped(leftToggle.isSelected());
    }//GEN-LAST:event_leftToggleActionPerformed

    private void showTeethToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showTeethToggleActionPerformed
        rambley.setRambleyJawClosed(showTeethToggle.isSelected());
    }//GEN-LAST:event_showTeethToggleActionPerformed

    private void mouthSpinnerXStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mouthSpinnerXStateChanged
        rambley.setRambleyOpenMouthWidth((Double)mouthSpinnerX.getValue()/100);
    }//GEN-LAST:event_mouthSpinnerXStateChanged

    private void mouthSpinnerYStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mouthSpinnerYStateChanged
        rambley.setRambleyOpenMouthHeight((Double)mouthSpinnerY.getValue()/100);
    }//GEN-LAST:event_mouthSpinnerYStateChanged

    private void scarfToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scarfToggleActionPerformed
        rambley.setRambleyScarfPainted(scarfToggle.isSelected());
    }//GEN-LAST:event_scarfToggleActionPerformed

    private void gridThickSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_gridThickSpinnerStateChanged
        rambley.setPixelGridLineThickness((float)gridThickSpinner.getValue());
    }//GEN-LAST:event_gridThickSpinnerStateChanged

    private void jSpinner7StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner7StateChanged
        setTestDoubleInConfig(TEST_DOUBLE_7_KEY);
    }//GEN-LAST:event_jSpinner7StateChanged

    private void jSpinner8StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner8StateChanged
        setTestDoubleInConfig(TEST_DOUBLE_8_KEY);
    }//GEN-LAST:event_jSpinner8StateChanged

    private void outlineToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outlineToggleActionPerformed
        rambley.setRambleyOutlinePainted(outlineToggle.isSelected());
    }//GEN-LAST:event_outlineToggleActionPerformed

    private void jSpinner9StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner9StateChanged
        setTestDoubleInConfig(TEST_DOUBLE_9_KEY);
    }//GEN-LAST:event_jSpinner9StateChanged

    private void jSpinner10StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner10StateChanged
        setTestDoubleInConfig(TEST_DOUBLE_10_KEY);
    }//GEN-LAST:event_jSpinner10StateChanged

    private void glitchyToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_glitchyToggleActionPerformed
        rambley.setRambleyGlitchy(glitchyToggle.isSelected());
    }//GEN-LAST:event_glitchyToggleActionPerformed

    private void hatToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hatToggleActionPerformed
        rambley.setConductorHatPainted(hatToggle.isSelected());
    }//GEN-LAST:event_hatToggleActionPerformed
    
    private Point2D getLineIntersection(double x, double y, Line2D line1, Line2D line2, 
            DoubleUnaryOperator getY1, DoubleUnaryOperator getY2){
        double x1 = Math.min(Math.min(line1.getX1(), line1.getX2()), Math.min(line2.getX1(), line2.getX2()));
        double x2 = Math.max(Math.max(line1.getX1(), line1.getX2()), Math.max(line2.getX1(), line2.getX2()));
        Point2D point = GeometryMath.getLineIntersection(x1, x2, getY1, getY2, null);
        point.setLocation(x+point.getX(), y+point.getY());
        return point;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RambleyTestViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RambleyTestViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RambleyTestViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RambleyTestViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RambleyTestViewer().setVisible(true);
            }
        });
    }
    
    private DebuggingIcon debugIcon;
    private RambleyPainter rambley;
    /**
     * This is a preference node to store the settings for this program.
     */
    private Preferences config;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox abTestingToggle;
    private javax.swing.JCheckBox backgroundToggle;
    private javax.swing.JSpinner bgDotSizeSpinner;
    private javax.swing.JSpinner bgDotSpacingSpinner;
    private javax.swing.JCheckBox circleDotToggle;
    private javax.swing.JCheckBox debugToggle;
    private javax.swing.JCheckBox evilToggle;
    private javax.swing.JFileChooser fc;
    private javax.swing.JCheckBox glitchyToggle;
    private javax.swing.JSpinner gridThickSpinner;
    private javax.swing.JCheckBox gridToggle;
    private javax.swing.JCheckBox hatToggle;
    private javax.swing.JSpinner heightSpinner;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner10;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JSpinner jSpinner7;
    private javax.swing.JSpinner jSpinner8;
    private javax.swing.JSpinner jSpinner9;
    private javax.swing.JSpinner jSpinnerA;
    private javax.swing.JCheckBox leftToggle;
    private javax.swing.JSpinner leftXSpinner;
    private javax.swing.JSpinner leftYSpinner;
    private javax.swing.JCheckBox linesToggle;
    private javax.swing.JCheckBox linkEyesToggle;
    private javax.swing.JCheckBox linkSizeToggle;
    private javax.swing.JCheckBox listenerToggle;
    private javax.swing.JSpinner mouthSpinnerX;
    private javax.swing.JSpinner mouthSpinnerY;
    private javax.swing.JCheckBox outlineToggle;
    private javax.swing.JSpinner pGridSpinner;
    private javax.swing.JButton printButton;
    private javax.swing.JCheckBox ratioToggle;
    private javax.swing.JButton resetButton;
    private javax.swing.JSpinner rightXSpinner;
    private javax.swing.JSpinner rightYSpinner;
    private javax.swing.JButton saveButton;
    private javax.swing.JCheckBox scaleToggle;
    private javax.swing.JCheckBox scarfToggle;
    private javax.swing.JCheckBox shadowToggle;
    private javax.swing.JCheckBox showTeethToggle;
    private components.JThumbnailLabel viewLabel;
    private javax.swing.JSpinner widthSpinner;
    // End of variables declaration//GEN-END:variables
    
    private class RambleyTestIcon extends RambleyPainter implements Icon2D{
        @Override
        public void paintIcon2D(Component c, Graphics2D g, int x, int y) {
            g.translate(x, y);
            paint(g, c, getIconWidth(), getIconHeight());
        }
        @Override
        public int getIconWidth() {
            if (widthSpinner == null)
                return 512;
            return (int)widthSpinner.getValue();
        }
        @Override
        public int getIconHeight() {
            if (linkSizeToggle == null || heightSpinner == null)
                return 512;
            if (linkSizeToggle.isSelected())
                return getIconWidth();
            return (int)heightSpinner.getValue();
        }
        @Override
        double getTestDouble1(){
            return (double) jSpinner1.getValue();
        }
        @Override
        double getTestDouble2(){
            return (double) jSpinner2.getValue();
        }
        @Override
        double getTestDouble3(){
            return (double) jSpinner3.getValue();
        }
        @Override
        double getTestDouble4(){
            return (double) jSpinner4.getValue();
        }
        @Override
        double getTestDouble5(){
            return (double) jSpinner5.getValue();
        }
        @Override
        double getTestDouble6(){
            return (double) jSpinner6.getValue();
        }
        @Override
        double getTestDouble7(){
            return (double) jSpinner7.getValue();
        }
        @Override
        double getTestDouble8(){
            return (double) jSpinner8.getValue();
        }
        @Override
        double getTestDouble9(){
            return (double) jSpinner9.getValue();
        }
        @Override
        double getTestDouble10(){
            return (double) jSpinner10.getValue();
        }
        @Override
        boolean getShowsLines(){
            return linesToggle.isSelected();
        }
        @Override
        boolean getABTesting(){
            return abTestingToggle.isSelected();
        }
    }
    
    private class IconHandler implements PropertyChangeListener{

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (listenerToggle.isSelected())
                System.out.println("Property Change: " + evt);
            viewLabel.repaint();
            if (config != null){
                if (RambleyPainter.FLAG_PROPERTY_NAMES_MAP.values().contains(evt.getPropertyName())){
                    updateConfigFlags();
                } else {
                    Double doubleValue = null;
                    Float floatValue = null;
                    if (evt.getNewValue() instanceof Double)
                        doubleValue = (Double) evt.getNewValue();
                    else if (evt.getNewValue() instanceof Float)
                        floatValue = (Float) evt.getNewValue();
                    
//                    switch(evt.getPropertyName()){
//                        case(RambleyPainter.BACKGROUND_DOT_SIZE_PROPERTY_CHANGED):
//                            doubleValue = rambleyIcon.getBackgroundDotSize();
//                            break;
//                        case(RambleyPainter.BACKGROUND_DOT_SPACING_PROPERTY_CHANGED):
//                            doubleValue = rambleyIcon.getBackgroundDotSpacing();
//                            break;
//                        case(RambleyPainter.PIXEL_GRID_LINE_SPACING_PROPERTY_CHANGED):
//                            doubleValue = rambleyIcon.getPixelGridLineSpacing();
//                            break;
//                        case(RambleyPainter.RAMBLEY_OPEN_MOUTH_WIDTH_PROPERTY_CHANGED):
//                            doubleValue = rambleyIcon.getRambleyOpenMouthWidth();
//                            break;
//                        case(RambleyPainter.RAMBLEY_OPEN_MOUTH_HEIGHT_PROPERTY_CHANGED):
//                            doubleValue = rambleyIcon.getRambleyOpenMouthHeight();
//                            break;
//                        case(RambleyPainter.RAMBLEY_RIGHT_EYE_X_PROPERTY_CHANGED):
//                            doubleValue = rambleyIcon.getRambleyRightEyeX();
//                            break;
//                        
//                        case(RambleyPainter.PIXEL_GRID_LINE_THICKNESS_PROPERTY_CHANGED):
//                            floatValue = rambleyIcon.getPixelGridLineThickness();
//                            break;
//                    }
                    if (doubleValue != null)
                        config.putDouble(evt.getPropertyName(), doubleValue);
                    else if (floatValue != null)
                        config.putFloat(evt.getPropertyName(), floatValue);
                }
            }
        }
    }
}
