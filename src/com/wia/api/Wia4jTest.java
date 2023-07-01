/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wia.api;

//import com.wia.*;
import com.wia.ClassFactory;
import com.wia.ICommonDialog;
import com.wia.IDevice;
import com.wia.WiaDeviceType;
import com.wia.WiaImageIntent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Naveed Quadri
 */
public abstract class Wia4jTest {

    public static void main(String[] args) {
        Wia4j wia4j = new Wia4j();
         //ICommonDialog dialog = ClassFactory.createCommonDialog();
         
//         IDevice scaner=dialog.showSelectDevice(WiaDeviceType.VideoDeviceType, true, true);
         
        Map<Integer, Integer> props = new HashMap<>();
//        props.put(Wia4j.WIA_IPS_XRES, 100); correct test
//        props.put(Wia4j.WIA_IPS_XRES, 100);
          props.put(Wia4j.WIA_IPS_MAX_HORIZONTAL_SIZE, 200);
          props.put(Wia4j.WIA_IPS_MAX_HORIZONTAL_SIZE, 200);
        //scan B&W
        //props.put(Wia4j., WiaImageIntent.TextIntent.comEnumValue());
        //props.put(Wia4j.WIA_IPS_PAGE_SIZE, 250);
        //props.put(Wia4j.WIA_IPS_PAGE_SIZE, 250);
        
        //WIA_IPS_PAGE_SIZE = WIA_PAGE_LETTER WIA_IPS_PAGE_WIDTH = 8500 WIA_IPS_PAGE_HEIGHT = 11000
        
        //props.put(Wia4j.WIA_IPS_PAGE_WIDTH, 300);
        try {
            //scan using the default dialogs from WIA
            //wia4j.scan("E:\\images\\test1.png");
            
            //wia4j.scan("E:\\images\\testimgCop.jpg",true, true, WiaImageFormat.wiaFormatJPEG, props);
            /**
             * Scans a single page from the feeder Warning! if there are
             * multiple pages in the feeder, the next page will be stucked in
             * the feeder!
             */
            //wia4j.scan("E:\\images\\test2.png", true, true, WiaImageFormat.wiaFormatPNG, props);
            //scans page from flatbed
            //wia4j.scan("E:\\images\\test3.png", false, true, WiaImageFormat.wiaFormatPNG, props);
            // change intent to color
            //props.remove(Wia4j.WIA_IPS_CUR_INTENT);
            props.put(Wia4j.WIA_IPS_CUR_INTENT, WiaImageIntent.ColorIntent.comEnumValue());
            //props.put(Wia4j.WIA_IPS_CUR_INTENT, WiaImageIntent.ColorIntent.comEnumValue());
            //int i=Wia4j.WIA_DPS_PAGE_SIZE;
            
            
            //wia4j.scan("E:\\images\\test4.png", true, true, WiaImageFormat.wiaFormatPNG, props);
            wia4j.scan("E:\\images\\abcd.png", false, true, WiaImageFormat.wiaFormatJPEG, props);
            System.out.println("***********");
            //wia4j.scan("E:\\images\\FARID1.jpg");
            //wia4j.
            //wia4j.getDeviceProperties().toString();
            
        } catch (WiaOperationException ex) {
            Logger.getLogger(Wia4jTest.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        //scan all pages in the feeder
       /*
        try {
            wia4j.scan("E:\\images", true, WiaImageFormat.wiaFormatJPEG, props, new ImageCallback<String>() {
                @Override
                public void handleImage(String image) {
                    System.out.println(image);
                }
            });
        } catch (WiaOperationException ex) {
            Logger.getLogger(Wia4jTest.class.getName()).log(Level.SEVERE, null, ex);
        }*/
       
    }
}
