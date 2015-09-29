package com.doadway.framework.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
  
public class ImageHelper {  
    /* 
     * 根据尺寸图片居中裁剪 
     */  
     @SuppressWarnings("unchecked")
	public static void cutCenterImage(String src,String dest,int w,int h) throws IOException{   
         Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");   
         ImageReader reader = (ImageReader)iterator.next();   
         InputStream in=new FileInputStream(src);  
         ImageInputStream iis = ImageIO.createImageInputStream(in);   
         reader.setInput(iis, true);   
         ImageReadParam param = reader.getDefaultReadParam();   
         int imageIndex = 0;   
         Rectangle rect = new Rectangle((reader.getWidth(imageIndex)-w)/2, (reader.getHeight(imageIndex)-h)/2, w, h);    
         param.setSourceRegion(rect);   
         BufferedImage bi = reader.read(0,param);     
         ImageIO.write(bi, "jpg", new File(dest));             
    
     }  
    /* 
     * 图片裁剪二分之一 
     */  
     @SuppressWarnings("unchecked")
	public static void cutHalfImage(String src,String dest) throws IOException{   
         Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");   
         ImageReader reader = (ImageReader)iterator.next();   
         InputStream in=new FileInputStream(src);  
         ImageInputStream iis = ImageIO.createImageInputStream(in);   
         reader.setInput(iis, true);   
         ImageReadParam param = reader.getDefaultReadParam();   
         int imageIndex = 0;   
         int width = reader.getWidth(imageIndex)/2;   
         int height = reader.getHeight(imageIndex)/2;   
         Rectangle rect = new Rectangle(width/2, height/2, width, height);   
         param.setSourceRegion(rect);   
         BufferedImage bi = reader.read(0,param);     
         ImageIO.write(bi, "jpg", new File(dest));     
     }  
    /* 
     * 图片裁剪通用接口 
     */  
  
    @SuppressWarnings("unchecked")
	public static void cutImage(String src,String dest,int x,int y,int w,int h) throws IOException{   
           Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");   
           ImageReader reader = (ImageReader)iterator.next();   
           InputStream in=new FileInputStream(src);  
           ImageInputStream iis = ImageIO.createImageInputStream(in);   
           reader.setInput(iis, true);   
           ImageReadParam param = reader.getDefaultReadParam();   
           Rectangle rect = new Rectangle(x, y, w,h);    
           param.setSourceRegion(rect);   
           BufferedImage bi = reader.read(0,param);     
           ImageIO.write(bi, "jpg", new File(dest));             
  
    }   
    /* 
     * 图片缩放 
     */  
    @SuppressWarnings("static-access")
	public static void zoomImage(String src,String dest,int w,int h) throws Exception {  
        double wr=0,hr=0;  
        File srcFile = new File(src);  
        File destFile = new File(dest);  
        BufferedImage bufImg = ImageIO.read(srcFile);  
        Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);  
        wr=w*1.0/bufImg.getWidth();  
        hr=h*1.0 / bufImg.getHeight();  
        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);  
        Itemp = ato.filter(bufImg, null);  
        try {  
            ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile);  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
          
    }  
   
    
    
    /**
     * 把图片印刷到图片上
     * 
     * @param pressImg --
     *            水印文件
     * @param targetImg --
     *            目标文件
     * @param x
     *            --x坐标
     * @param y
     *            --y坐标
     */
    public  static void pressImage(String pressImg, String targetImg,
            int x, int y) {
        try {
            //目标文件
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);

            //水印文件
            File _filebiao = new File(pressImg);
            Image src_biao = ImageIO.read(_filebiao);
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            g.drawImage(src_biao, x,y, wideth_biao, height_biao, null);
            //水印文件结束
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印文字水印图片
     * 
     * @param pressText
     *            --文字
     * @param targetImg --
     *            目标图片
     * @param fontName --
     *            字体名
     * @param fontStyle --
     *            字体样式
     * @param color --
     *            字体颜色
     * @param fontSize --
     *            字体大小
     * @param x --
     *            偏移量
     * @param y
     */

    public static void pressText(String pressText, String targetImg,
            String fontName, int fontStyle, int color, int fontSize, int x,
            int y) {
        try {
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);
            g.setColor(Color.RED);
            g.setFont(new Font(fontName, fontStyle, fontSize));

            g.drawString(pressText, wideth - fontSize - x, height - fontSize
                    / 2 - y);
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
      
    public static void main(String[] args) {  
    	ImageHelper.pressImage("C:/Documents and Settings/Administrator/My Documents/Winter.jpg","C:/Documents and Settings/Administrator/My Documents/Winter123.jpg",400,300);
    	System.out.println("执行完毕");
    }      
}  