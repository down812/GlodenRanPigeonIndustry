//package com.sky.utils;
//
//import net.bramp.ffmpeg.FFmpeg;
//import net.bramp.ffmpeg.FFmpegExecutor;
//import net.bramp.ffmpeg.builder.FFmpegBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//
//import javax.annotation.PostConstruct;
//import java.io.File;
//
//@Component
//public class StreamConverter {
//    private FFmpegExecutor executor;
//
//    @Value("${ffmpeg.path}")
//    private String ffmpegPath;
//
//    @Value("${hls.output-dir}")
//    private String outputDir;
//
//    @PostConstruct
//    public void init() throws Exception {
//        this.executor = new FFmpegExecutor(new FFmpeg(ffmpegPath));
//        new File(outputDir).mkdirs();
//    }
//
//    /**
//     *  将RTSP流转换为HLS流
//     * @param rtspUrl
//     * @return
//     */
//    public String convertRtspToHls(String rtspUrl) {
//        String streamId = java.util.UUID.randomUUID().toString();
//        String outputPath = outputDir + File.separator + streamId;
//
//        // 确保输出目录存在
//        File outputDirFile = new File(outputPath);
//        if (!outputDirFile.exists()) {
//            outputDirFile.mkdirs();
//        }
//
//        FFmpegBuilder builder = new FFmpegBuilder()
//                .setInput(rtspUrl)
//                .overrideOutputFiles(true)
//                .addOutput(outputPath + "/playlist.m3u8")
//                .setFormat("hls")
//                .addExtraArgs("-hls_time", "10")
//                .addExtraArgs("-hls_list_size", "6")
//                .addExtraArgs("-hls_flags", "delete_segments")
//                .addExtraArgs("-c:v", "libx264") // 强制使用H.264编码
//                .addExtraArgs("-c:v", "h264_cuvid")  // 如果使用NVIDIA硬解
//                .addExtraArgs("-c:v", "hevc")        // 如果使用CPU软解
//                .done();
//
//        try {
//            executor.createJob(builder).run();
//            return streamId;
//        } catch (Exception e) {
//            // 删除失败时创建的目录
//            if (outputDirFile.exists()) {
//                outputDirFile.delete();
//            }
//            throw new RuntimeException("RTSP转码失败: " + e.getMessage(), e);
//        }
//    }
//}