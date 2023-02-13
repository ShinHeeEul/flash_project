package flash.flash.STT;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SpeechToText {

    public SpeechToText(String filePath) {
        recognitionSpeech(filePath);
    }

    //1분 미만 음성 파일은 바로 분석
    public static void recognitionSpeech(String filePath) {

        try {
            SpeechClient speech = SpeechClient.create();

            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                    .setSampleRateHertz(16000)
                    .setLanguageCode("en-US")
                    .build();

            RecognitionAudio audio = getRecognitionAudio(filePath);
            RecognizeResponse response = speech.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            for(SpeechRecognitionResult result: results) {
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription : %s%n", alternative.getTranscript());
            }
            speech.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

// 1분 이상 음성 파일은 google storage에 올려서 분석
    public static RecognitionAudio getRecognitionAudio(String filePath) throws IOException {
        RecognitionAudio recognitionAudio;

        if(filePath.startsWith("gs://")) {
            recognitionAudio = RecognitionAudio.newBuilder()
                    .setUri(filePath)
                    .build();
        }
        else {
            Path path = Paths.get(filePath);
            byte[] data = Files.readAllBytes(path);
            ByteString audioBytes = ByteString.copyFrom(data);

            recognitionAudio = RecognitionAudio.newBuilder()
                    .setContent(audioBytes)
                    .build();
        }

        return recognitionAudio;
    }
}
