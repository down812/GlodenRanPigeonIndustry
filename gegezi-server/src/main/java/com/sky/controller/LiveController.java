package com.sky.controller;
import com.sky.properties.ZegoProperties;
import com.sky.utils.ZegoTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.sky.result.Result;
//import com.sky.utils.StreamConverter;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/live")
@Api(tags = "直播相关接口")
@CrossOrigin
public class LiveController {

   /* @Autowired
    private StreamConverter streamConverter;*/

    @Autowired
    private ZegoProperties zegoProperties;

    /*@PostMapping("/start")
    @ApiOperation("启动RTSP转码")
    public Result<String> startLiveStream(
            @ApiParam(value = "RTSP地址", required = true) @RequestParam String rtspUrl,
            @ApiParam(value = "用户Token", required = true) @RequestParam String token) {

        // 验证token
        boolean isValid = ZegoTokenUtil.verifyToken(token, zegoProperties.getServerSecret());
        if (!isValid) {
            return Result.error("无效的Token，请重新获取");
        }

        String streamId = streamConverter.convertRtspToHls(rtspUrl);
        return Result.success("/hls/" + streamId + "/playlist.m3u8");
    }*/

    @GetMapping("/generate")
    @ApiOperation("生成监控接口Token")
    public Result<String> generateToken(@ApiParam(value = "用户ID", required = true) @RequestParam String userId) {
        String token = ZegoTokenUtil.generateToken04(
                zegoProperties.getAppId(),
                zegoProperties.getServerSecret(),
                userId,
                zegoProperties.getTokenTtl()
        );
        return Result.success(token);
    }



    @PostMapping("/verify")
    @ApiOperation("验证监控接口Token有效性")
    public Result<Boolean> verifyToken(
            @ApiParam(value = "待验证的Token", required = true) @RequestParam String token) {
        boolean isValid = ZegoTokenUtil.verifyToken(token, zegoProperties.getServerSecret());
        return Result.success(isValid);
    }

}