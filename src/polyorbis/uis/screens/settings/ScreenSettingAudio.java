package polyorbis.uis.screens.settings;

import flounder.devices.*;
import flounder.events.*;
import flounder.guis.*;
import flounder.maths.*;
import flounder.maths.vectors.*;
import polyorbis.uis.*;
import polyorbis.uis.screens.*;

public class ScreenSettingAudio extends ScreenObject {
	public ScreenSettingAudio(OverlaySlider slider, ScreenSettings settings) {
		super(slider, new Vector2f(0.5f, 0.5f), new Vector2f(1.0f, 1.0f));
		super.setInScreenCoords(false);

		// Toggle Music.
		GuiButtonText toggleMusic = new GuiButtonText(this, new Vector2f(0.5f, 0.20f), "Music Enabled: ", GuiAlign.CENTRE);
		FlounderEvents.get().addEvent(new EventChange<Boolean>(FlounderSound.get().getMusicPlayer()::isPaused) {
			@Override
			public void onEvent(Boolean newValue) {
				toggleMusic.setText("Music Enabled: " + !newValue);
			}
		});
		toggleMusic.addLeftListener(() -> {
			if (!FlounderSound.get().getMusicPlayer().isPaused()) {
				FlounderSound.get().getMusicPlayer().pauseTrack();
			} else {
				FlounderSound.get().getMusicPlayer().unpauseTrack();
			}
		});

		// Slider Music Volume.
		GuiSliderText sliderMusicVolume = new GuiSliderText(this, new Vector2f(0.5f, 0.27f), "Music Volume: ", 0.0f, 1.0f, FlounderSound.get().getMusicPlayer().getVolume(), GuiAlign.CENTRE);
		FlounderEvents.get().addEvent(new EventChange<Float>(FlounderSound.get().getMusicPlayer()::getVolume) {
			@Override
			public void onEvent(Float newValue) {
				sliderMusicVolume.setText("Music Volume: " + Maths.roundToPlace(newValue, 3));
			}
		});
		sliderMusicVolume.addChangeListener(() -> FlounderSound.get().getMusicPlayer().setVolume(sliderMusicVolume.getProgress()));

		// Slider Sound Volume.
		GuiSliderText sliderSoundVolume = new GuiSliderText(this, new Vector2f(0.5f, 0.34f), "Sound Volume: ", 0.0f, 1.0f, FlounderSound.get().getSourcePool().getSystemVolume(), GuiAlign.CENTRE);
		FlounderEvents.get().addEvent(new EventChange<Float>(FlounderSound.get().getSourcePool()::getSystemVolume) {
			@Override
			public void onEvent(Float newValue) {
				sliderSoundVolume.setText("Sound Volume: " + Maths.roundToPlace(newValue, 3));
			}
		});
		sliderSoundVolume.addChangeListener(() -> FlounderSound.get().getSourcePool().setSystemVolume(sliderSoundVolume.getProgress()));

		// Back.
		GuiButtonText back = new GuiButtonText(this, new Vector2f(0.5f, 0.9f), "Back", GuiAlign.CENTRE);
		back.addLeftListener(() -> slider.setNewSecondaryScreen(settings));
	}

	@Override
	public void updateObject() {
	}

	@Override
	public void deleteObject() {

	}
}
