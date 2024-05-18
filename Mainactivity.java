package com.example.media1;
import androidx.appcompat.app.AppCompatActivity; import android.os.Bundle;
import android.media.MediaPlayer; import android.os.Bundle;
import android.view.View; import android.widget.Button; import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity; public class MainActivity extends AppCompatActivity {
private MediaPlayer mediaPlayer; private Button btnPlayPause; private SeekBar seekBar;
private boolean isPlaying;

@Override
protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);

mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio); btnPlayPause = findViewById(R.id.btnPlayPause);
seekBar = findViewById(R.id.seekBar); seekBar.setMax(mediaPlayer.getDuration());
btnPlayPause.setOnClickListener(new View.OnClickListener() { @Override
public void onClick(View v) { if (isPlaying) {
pauseAudio();
} else {
playAudio();
}
}
});

seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { @Override
public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { if (fromUser) {
mediaPlayer.seekTo(progress);
}
}

@Override
 
public void onStartTrackingTouch(SeekBar seekBar) {
}

@Override
public void onStopTrackingTouch(SeekBar seekBar) {
}
});

mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { @Override
public void onCompletion(MediaPlayer mp) { pauseAudio();
seekBar.setProgress(0);
}
});
}

private void playAudio() { mediaPlayer.start(); btnPlayPause.setText("Pause"); isPlaying = true;
}

private void pauseAudio() { mediaPlayer.pause(); btnPlayPause.setText("Play"); isPlaying = false;
}

@Override
protected void onDestroy() { super.onDestroy();
if (mediaPlayer != null) { mediaPlayer.release(); mediaPlayer = null;
}
}
}
