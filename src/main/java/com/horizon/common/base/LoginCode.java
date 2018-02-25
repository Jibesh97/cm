//package com.horizon.common.base;
// 
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.Random;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//
//
//public class LoginCode extends HttpServlet {
//    /**
//     * <p>
//     * Discription:
//     * </p>
//     */
//    private static final long serialVersionUID = 1L;
//    /**
//     * 设置字体
//     */
//    private static Font mFont = new Font("Times New Roman", Font.PLAIN, 18);
//    /**
//     * 图片宽度默认�?
//     */
//    private static int PIC_WIDTH = 60;
//    /**
//     * 图片高度默认�?
//     */
//    private static int PIC_HEIGHT = 20;
//    /**
//     * 验证码个�?
//     */
//    private static int CODE_NUM = 4;
//    /**
//     * 验证码的属�?�名
//     */
//    public static final String CODE_ATTRIBUTE = "AuthCode";
//
//    public void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        doGet(request, response);
//    }
//
//    private void getParameter(HttpServletRequest request) {
//        if (request.getParameter("width") != null
//                && !"".equals(request.getParameter("width"))) {
//            PIC_WIDTH = Integer.parseInt(request.getParameter("width"));
//        }
//        if (request.getParameter("height") != null
//                && !"".equals(request.getParameter("height"))) {
//            PIC_HEIGHT = Integer.parseInt(request.getParameter("height"));
//        }
//        if (request.getParameter("code_num") != null
//                && !"".equals(request.getParameter("code_num"))) {
//            CODE_NUM = Integer.parseInt(request.getParameter("code_num"));
//        }
//        if (request.getParameter("font_size") != null
//                && !"".equals(request.getParameter("font_size"))) {
//            int fontSize = Integer.parseInt(request.getParameter("font_size"));
//            mFont = new Font("Times New Roman", Font.PLAIN, fontSize);
//        }
//    }
//
//    public void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();// request.getSession(false)当参数为false时，有可能不创建session
//        response.setContentType("image/gif");
//        response.setHeader("Pragma", "No-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);
//        getParameter(request);// 取得参数
//        ServletOutputStream out = response.getOutputStream();
//        BufferedImage image = new BufferedImage(PIC_WIDTH, PIC_HEIGHT,
//                BufferedImage.TYPE_INT_RGB); // 设置图片大小�?
//        Graphics gra = image.getGraphics();
//        Random random = new Random();
//
//        gra.setColor(new Color(30, 65, 109)); // 设置背景�?
//        gra.fillRect(0, 0, PIC_WIDTH, PIC_HEIGHT);
//
//        gra.setFont(mFont);
//        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测�?
//        gra.setColor(getRandColor(160, 200));
//        int width = 60, height = 20; 
//        for (int i = 0; i < 50; i++) {
//            int x = random.nextInt(width);
//            int y = random.nextInt(height);
//            int xl = random.nextInt(12);
//            int yl = random.nextInt(12);
//            gra.drawLine(x, y, x + xl, y + yl);
//        }
//
//        // 取随机产生的认证�?(4位数�?)
//        String sRand = "";
//        for (int i = 0; i < CODE_NUM; i++) {
//            String rand = String.valueOf(random.nextInt(10));
//            sRand += rand;
//            // 将认证码显示到图象中
//            // gra.setColor(new Color(20 + random.nextInt(110), 20 +
//            // random.nextInt(110), 20 +
//            // random.nextInt(110)));//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生�?
//            gra.setColor(Color.white);
//            gra.drawString(rand, 13 * i + 6, 16);
//        }
//        session.setAttribute(CODE_ATTRIBUTE, sRand);
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//        encoder.encode(image);
//    }
//
//    private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
//        Random random = new Random();
//        if (fc > 255)
//            fc = 255;
//        if (bc > 255)
//            bc = 255;
//        int r = fc + random.nextInt(bc - fc);
//        int g = fc + random.nextInt(bc - fc);
//        int b = fc + random.nextInt(bc - fc);
//        return new Color(r, g, b);
//    }
//
//}
