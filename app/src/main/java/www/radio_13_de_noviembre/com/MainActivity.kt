package www.radio_13_de_noviembre.com


import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.io.IOException

class MainActivity : AppCompatActivity() {
    // lateinit var textWiew : TextView
    private lateinit var playBtn : de.hdodenhof.circleimageview.CircleImageView
    private lateinit var facebook : ImageButton
    private lateinit var shareBtn : ImageButton
    private lateinit var btnTikTok : ImageButton
    private lateinit var ytbBtn : ImageButton
    private lateinit var wspBtn : ImageButton
    private lateinit var gifImg : pl.droidsonroids.gif.GifImageView

    var conf = true

    var mediaPlayer:MediaPlayer? = null

    private val BASE_URL = "https://https://awanahost.com/"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Radio_13_de_noviembre)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playAudio()
        facebook = findViewById(R.id.facebook)

        facebook.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_VIEW)
            myIntent.setData(Uri.parse("https://www.facebook.com/radiotv13denoviembre"))
            startActivity(myIntent)
        }

        ytbBtn = findViewById(R.id.youtube)

        ytbBtn.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_VIEW)
            myIntent.setData(Uri.parse("https://www.youtube.com/channel/UCY8pOg4FUWHG_fpOLnM6g1Q"))
            startActivity(myIntent)
        }
        btnTikTok = findViewById(R.id.tiktok)

        btnTikTok.setOnClickListener{
            val myIntent2 = Intent(Intent.ACTION_VIEW)
            myIntent2.setData(Uri.parse("https://www.tiktok.com/@radio13denoviembre"))
            startActivity(myIntent2)
        }

        wspBtn = findViewById(R.id.wsp)

        wspBtn.setOnClickListener{
            val myIntent2 = Intent(Intent.ACTION_VIEW)
            myIntent2.setData(Uri.parse("https://wa.me/59171796669"))
            startActivity(myIntent2)
        }

        shareBtn = findViewById(R.id.share)
        //textWiew = findViewById(R.id.textEnVivo)
        //YoYo.with(Techniques.Flash).duration(5000).repeat(500).playOn(textEnVivo)


        //Share buttom
        shareBtn.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.type = "type/pain"
            val shareBody = "Download this App"
            val shareSub = BASE_URL
            myIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody)
            myIntent.putExtra(Intent.EXTRA_TEXT, shareSub)
            startActivity(Intent.createChooser( myIntent, "share your app"))
        }

        playBtn = findViewById(R.id.playBtn)


        playBtn.setOnClickListener {
            gifImg = findViewById(R.id.gif)
            if (!conf) {
                playBtn.setImageResource(R.mipmap.app_stop_btn)
                gifImg.setImageResource(R.mipmap.onda_sonido)
                playAudio()
                conf=true
            }
            else{
                if (conf) {
                    pauseAudio()
                    conf=false
                    playBtn.setImageResource(R.mipmap.app_play_btn)
                    gifImg.setImageResource(R.mipmap.onda_sonido_calmado)

                }
            }
        }
    }
    private fun playAudio() {
        val audioUrl = "https://radio.awanahost.com/8014/stream"
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)

        try {
            mediaPlayer!!.setDataSource(audioUrl)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()

        }catch (e: IOException){
            e.printStackTrace()
        }
        Toast.makeText(this, "Audio Started playing", Toast.LENGTH_SHORT).show()
    }

    private fun pauseAudio() {
        if (mediaPlayer!!.isPlaying){
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
        }else{
            Toast.makeText(this, "Audio has not played", Toast.LENGTH_SHORT).show()
        }
    }

}