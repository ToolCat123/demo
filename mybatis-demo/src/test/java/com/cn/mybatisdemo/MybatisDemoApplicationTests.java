package com.cn.mybatisdemo;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

@SpringBootTest
class MybatisDemoApplicationTests {
    public static final Logger log = LoggerFactory.getLogger(MybatisDemoApplicationTests.class);

    @Test
    void contextLoads() {
//        System.out.println(new Date());
//
//        String s1 = this.generateCover("1000004$1$0$0", "http://112.51.15.48:7086/live/cameraid/1000004%240/substream/1.m3u8?token=81dbeb1ef0b141408fabb2d0f82f7e90");
//        System.out.println("s1 = " + s1);
//        System.out.println(new Date());

//        String s2 = this.generateCover("1000005$1$0$0", "http://112.51.15.48:7086/live/cameraid/1000005%240/substream/1.m3u8?token=81dbeb1ef0b141408fabb2d0f82f7e90");
//        System.out.println("s2 = " + s2);
//        System.out.println(new Date());
//
//        String s3 = this.generateCover("1000006$1$0$0", "http://112.51.15.48:7086/live/cameraid/1000006%240/substream/1.m3u8?token=81dbeb1ef0b141408fabb2d0f82f7e90");
//        System.out.println("s3 = " + s3);
//        System.out.println(new Date());
//
//        String s4 = this.generateCover("1kC5ucXSA1CMGDF30HAB0N", "http://112.51.15.48:8050/cam/realmonitor/1kC5ucXSA1CMGDF30HAB0N?subtype=0&streamType=0&token=1618940089_57ab9106349efb69abcc06e3ee721536be8e1700&mediatype=HLS.m3u8");
//        System.out.println("s4 = " + s4);
//        System.out.println(new Date());

        String filePath = "/home/dahua/1000004$1$0$0.jpg";
        String videoPath = "D:/workspace/cff6670c22184eeaddc23db41c247bb7.mp4";
        getFirstFrame(filePath, videoPath);

        String path = "http://112.51.15.48:7086/live/cameraid/1000004%240/substream/1.m3u8?token=bdb8b2cecb1740eab66ff4880ef930c4";
        getFirstPicByStream(path, filePath);
    }

//    private String generateCover(String uuid, String input) {
//        ProgramConfig programConfig = new ProgramConfig();
//        programConfig.setDeBugLog(false);
//        VideoEditing.init(programConfig);
////        String output = "/home/" + uuid;
//        String output = "/home/";
//        System.out.println("output = " + output);
//        VideoEditing.grabbingFrameToJpg(input, output, "00:00:01", "1", 1, 64, null);
//        File rename = FileUtil.rename(new File("/home/1.jpeg"), uuid, true, true);
//        return rename.getAbsolutePath();
////        return output + "/1.jpeg";
//    }

    private void getFirstFrame(String filePath, String videoPath) {
        FFmpegFrameGrabber ff = null;
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            File fileVideo = new File(videoPath);
            if (fileVideo.exists()) {
                System.out.println("文件存在，路径正确！");
                ff = new FFmpegFrameGrabber(fileVideo);
                ff.start();
                int ftp = ff.getLengthInFrames();
                int flag = 0;
                Frame frame = null;
                while (flag <= ftp) {
                    //获取帧
                    frame = ff.grabImage();
                    //过滤前3帧，避免出现全黑图片
                    if ((flag > 3) && (frame != null)) {
                        break;
                    }
                    flag++;
                }
                //ImageIO.write(FrameToBufferedImage(frame), "jpg", file);
                frameToBufferedImage(frame, file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ff != null) {
                    ff.close();
                    ff.stop();
                }
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static RenderedImage frameToBufferedImage(Frame frame) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        return converter.getBufferedImage(frame);
    }

    private void frameToBufferedImage(Frame frame, File file) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage fecthedImage = converter.getBufferedImage(frame);
        BufferedImage bi = new BufferedImage(1920, 1080, BufferedImage.TYPE_3BYTE_BGR);
        bi.getGraphics().drawImage(fecthedImage.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH), 0, 0, null);
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        try {
            ImageIO.write(bi, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getFirstPicByStream(String streamUrl, String filePath) {
        //String streamURL="rtmp://xxx.xxxxx.com/appName/1591250292?auth_key=1591252092-0-0-e996f5d75ca9c86fb57c9e3a3cb47bf4";
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        // 获取视频源
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(streamUrl);
        try {
            grabber.start();
            int ftp = grabber.getLengthInFrames();
            int flag = 0;
            Frame frame = null;
            while (flag <= ftp) {
                //获取帧
                frame = grabber.grabImage();
                //过滤前3帧，避免出现全黑图片
                if ((flag > 3) && (frame != null)) {
                    break;
                }
                flag++;
            }
            frameToBufferedImage(frame, file);
        } catch (FrameGrabber.Exception e) {
            log.error("获取视频第一帧异常:{}", e.getMessage());
        } finally {
            try {
                grabber.stop();
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
            }
        }
    }
}
