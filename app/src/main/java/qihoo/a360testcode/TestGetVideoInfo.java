package qihoo.a360testcode;



        import com.j256.simplemagic.ContentInfo;
        import com.j256.simplemagic.ContentInfoUtil;
        import com.xuggle.xuggler.ICodec;
        import com.xuggle.xuggler.IContainer;
        import com.xuggle.xuggler.IStream;
        import com.xuggle.xuggler.IStreamCoder;
        import it.sauronsoftware.jave.Encoder;
        import it.sauronsoftware.jave.EncoderException;
        import it.sauronsoftware.jave.MultimediaInfo;

        import java.io.*;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {

        //使用xuggler来解析视频，音频，这里可以得到音视频的所有信息
        IContainer container = IContainer.make();
        int result = container.open("E:\\下载\\audio\\陈冰-盛夏光年.mp4",IContainer.Type.READ,null);
        if(result < 0){
            throw new Exception("Failed to open media file");
        }

        System.out.println(container);

        System.out.println("Number of streams: " + container.getNumStreams());
        System.out.println("Duration (ms): " +container.getDuration());
        System.out.println("File Size (bytes): " + container.getFileSize());
        System.out.println("Bit Rate: " + container.getBitRate());

        // iterate through the streams to print their meta data
        for (int i=0; i<container.getNumStreams(); i++) {

            // find the stream object
            IStream stream = container.getStream(i);

            // get the pre-configured decoder that can decode this stream;
            IStreamCoder coder = stream.getStreamCoder();

            System.out.println("*** Start of Stream Info ***");

            System.out.printf("stream %d: ", i);
            System.out.printf("type: %s; ", coder.getCodecType());
            System.out.printf("codec: %s; ", coder.getCodecID());
            System.out.printf("duration: %s; ", stream.getDuration());
            System.out.printf("start time: %s; ", container.getStartTime());
            System.out.printf("timebase: %d/%d; ",
                    stream.getTimeBase().getNumerator(),
                    stream.getTimeBase().getDenominator());
            System.out.printf("coder tb: %d/%d; ",
                    coder.getTimeBase().getNumerator(),
                    coder.getTimeBase().getDenominator());
            System.out.println();

            if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_AUDIO) {
                System.out.printf("sample rate: %d; ", coder.getSampleRate());
                System.out.printf("channels: %d; ", coder.getChannels());
                System.out.printf("format: %s", coder.getSampleFormat());
            }
            else if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO) {
                System.out.printf("width: %d; ", coder.getWidth());
                System.out.printf("height: %d; ", coder.getHeight());
                System.out.printf("format: %s; ", coder.getPixelType());
                System.out.printf("frame-rate: %5.2f; ", coder.getFrameRate().getDouble());
            }

            System.out.println();
            System.out.println("*** End of Stream Info ***");

        }


        ////使用jave来解析视频，音频，这里拿不到音视频的bitRate
        //File file = new File("E:\\下载\\audio\\陈冰-盛夏光年.mp4");
        //
        //Encoder encoder = new Encoder();
        //
        //MultimediaInfo info = encoder.getInfo(file);
        //System.out.println(info.getVideo().getBitRate());
        //
        //
        //System.out.println(info);


        //使用simplemagic来判断文件类型，这里用来区分哪些是音视频，哪些是图片
        //simplemagic参考资料：https://github.com/j256/simplemagic/
        //simplemagic可直接在maven仓库中获取
        ContentInfoUtil util = new ContentInfoUtil();
        ContentInfo info = util.findMatch("E:\\下载\\audio\\陈冰-盛夏光年.mp4");
        if(info == null){
            System.out.println("Unknown content-type");
        } else {
            System.out.println(info.getName());
            System.out.println(info);
        }
        System.out.println("------------------------------------------");

        info = util.findMatch("E:\\下载\\image\\error.xml");
        if(info == null){
            System.out.println("Unknown content-type");
        } else {
            System.out.println(info.getName());
            System.out.println(info);
        }
    }
}


