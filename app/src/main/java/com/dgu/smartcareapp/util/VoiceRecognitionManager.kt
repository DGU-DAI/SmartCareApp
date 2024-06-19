package com.dgu.smartcareapp.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import javax.inject.Inject

class VoiceRecognitionManager @Inject constructor(
    private val context: Context
) {

    private var speechRecognizer: SpeechRecognizer? = null
    var onSafeWordDetected: ((String) -> Unit)? = null
    var isListening = false
    private var safeWords: List<String> = emptyList()

    fun setSafeWords(words: List<String>) {
        safeWords = words
    }

    fun startListening() {
        if (isListening) return

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context).apply {
            setRecognitionListener(object : RecognitionListener {
                override fun onReadyForSpeech(params: Bundle?) {
                    Log.d("VoiceRecognition", "음성 인식을 시작할 준비가 되었습니다.")
                }

                override fun onBeginningOfSpeech() {
                    Log.d("VoiceRecognition", "음성 입력이 시작되었습니다.")
                }

                override fun onRmsChanged(rmsdB: Float) {
                    Log.d("VoiceRecognition", "RMS 변화: $rmsdB")
                }

                override fun onBufferReceived(buffer: ByteArray?) {
                    Log.d("VoiceRecognition", "버퍼가 수신되었습니다.")
                }

                override fun onEndOfSpeech() {
                    Log.d("VoiceRecognition", "음성 입력이 종료되었습니다.")
                }

                override fun onError(error: Int) {
                    Log.d("VoiceRecognition", "오류 발생: $error")
                    restartListening()
                }

                override fun onResults(results: Bundle?) {
                    Log.d("VoiceRecognition", "결과가 수신되었습니다.")
                    results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                        ?.forEach { result ->
                            Log.d("VoiceRecognition", "결과: $result")
                            Log.d("VoiceRecognition", "세이프 워드 리스트: ${safeWords}")
                            if (result in safeWords) {
                                Log.d("VoiceRecognition", "안전 단어 감지: $result")
                                onSafeWordDetected?.invoke(result)
                            }
                        }
                    restartListening()
                }

                override fun onPartialResults(partialResults: Bundle?) {
                    Log.d("VoiceRecognition", "부분 결과가 수신되었습니다.")
                }

                override fun onEvent(eventType: Int, params: Bundle?) {
                    Log.d("VoiceRecognition", "이벤트 발생: $eventType")
                }
            })
        }
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        }
        speechRecognizer?.startListening(intent)
        isListening = true
    }

    private fun restartListening() {
        if (isListening) {
            stopListening()
            startListening()
        }
    }

    fun stopListening() {
        Log.d("VoiceRecognition", "음성 인식을 중지합니다.")
        isListening = false
        speechRecognizer?.stopListening()
        speechRecognizer?.destroy()
        speechRecognizer = null
    }
}
