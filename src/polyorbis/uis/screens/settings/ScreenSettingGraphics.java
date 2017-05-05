package polyorbis.uis.screens.settings;

import flounder.devices.*;
import flounder.events.*;
import flounder.fbos.*;
import flounder.framework.*;
import flounder.guis.*;
import flounder.maths.*;
import flounder.maths.vectors.*;
import flounder.platform.*;
import flounder.shadows.*;
import flounder.textures.*;
import polyorbis.uis.*;
import polyorbis.uis.screens.*;

public class ScreenSettingGraphics extends ScreenObject {
	public ScreenSettingGraphics(OverlaySlider slider, ScreenSettings settings) {
		super(slider, new Vector2f(0.5f, 0.5f), new Vector2f(1.0f, 1.0f));
		super.setInScreenCoords(false);

		// Left and right Panes.
		ScreenObject paneLeft = new ScreenObjectEmpty(this, new Vector2f(0.25f, 0.5f), new Vector2f(0.5f, 1.0f), true);
		ScreenObject paneRight = new ScreenObjectEmpty(this, new Vector2f(0.75f, 0.5f), new Vector2f(0.5f, 1.0f), true);

		// Toggle Antialiasing.
		GuiButtonText toggleAntialiasing = new GuiButtonText(paneLeft, new Vector2f(0.25f, 0.20f), "Is Antialiasing: ", GuiAlign.CENTRE);
		FlounderEvents.get().addEvent(new EventChange<Boolean>(FlounderDisplay.get()::isAntialiasing) {
			@Override
			public void onEvent(Boolean newValue) {
				toggleAntialiasing.setText("Is Antialiasing: " + newValue);
			}
		});
		toggleAntialiasing.addLeftListener(new ScreenListener() {
			@Override
			public void eventOccurred() {
				FlounderDisplay.get().setAntialiasing(!FlounderDisplay.get().isAntialiasing());
			}
		});

		// Toggle Fullscreen.
		GuiButtonText toggleFullscreen = new GuiButtonText(paneLeft, new Vector2f(0.25f, 0.27f), "Is Fullscreen: ", GuiAlign.CENTRE);
		FlounderEvents.get().addEvent(new EventChange<Boolean>(FlounderDisplay.get()::isFullscreen) {
			@Override
			public void onEvent(Boolean newValue) {
				toggleFullscreen.setText("Is Fullscreen: " + newValue);
			}
		});
		toggleFullscreen.addLeftListener(new ScreenListener() {
			@Override
			public void eventOccurred() {
				FlounderDisplay.get().setFullscreen(!FlounderDisplay.get().isFullscreen());
			}
		});

		// Toggle Vsync.
		GuiButtonText toggleVsync = new GuiButtonText(paneLeft, new Vector2f(0.25f, 0.34f), "VSync Enabled: ", GuiAlign.CENTRE);
		FlounderEvents.get().addEvent(new EventChange<Boolean>(FlounderDisplay.get()::isVSync) {
			@Override
			public void onEvent(Boolean newValue) {
				toggleVsync.setText("VSync Enabled: " + newValue);
			}
		});
		toggleVsync.addLeftListener(new ScreenListener() {
			@Override
			public void eventOccurred() {
				FlounderDisplay.get().setVSync(!FlounderDisplay.get().isVSync());
			}
		});

		// Slider Limit FPS.
		GuiSliderText sliderLimitFPS = new GuiSliderText(paneLeft, new Vector2f(0.25f, 0.41f), "FPS Limit: ", 20.0f, 1100.0f, Framework.getFpsLimit(), GuiAlign.CENTRE);
		FlounderEvents.get().addEvent(new EventChange<Integer>(Framework::getFpsLimit) {
			@Override
			public void onEvent(Integer newValue) {
				sliderLimitFPS.setText("FPS Limit: " + (newValue > 1000.0f ? "infinite" : newValue));
			}
		});
		sliderLimitFPS.addChangeListener(new ScreenListener() {
			@Override
			public void eventOccurred() {
				Framework.setFpsLimit((int) sliderLimitFPS.getProgress());
			}
		});

		// Slider Texture Anisotropy.
		GuiSliderText sliderTextureAnisotropy = new GuiSliderText(paneLeft, new Vector2f(0.25f, 0.48f), "Texture Anisotropy: ", 0.0f, FlounderPlatform.get().getMaxAnisotropy(), FlounderTextures.get().getAnisotropyLevel(), GuiAlign.CENTRE);
		FlounderEvents.get().addEvent(new EventChange<Float>(FlounderTextures.get()::getAnisotropyLevel) {
			@Override
			public void onEvent(Float newValue) {
				sliderTextureAnisotropy.setText("Texture Anisotropy: " + Maths.roundToPlace(newValue, 1));
			}
		});
		sliderTextureAnisotropy.addChangeListener(new ScreenListener() {
			@Override
			public void eventOccurred() {
				FlounderTextures.get().setAnisotropyLevel(sliderTextureAnisotropy.getProgress());
			}
		});

		// Slider Brightness Boost.
		GuiSliderText sliderBrightnessBoost = new GuiSliderText(paneLeft, new Vector2f(0.25f, 0.55f), "Brightness Boost: ", -0.3f, 0.8f, FlounderShadows.get().getBrightnessBoost(), GuiAlign.CENTRE);
		FlounderEvents.get().addEvent(new EventChange<Float>(FlounderShadows.get()::getBrightnessBoost) {
			@Override
			public void onEvent(Float newValue) {
				sliderBrightnessBoost.setText("Brightness Boost: " + Maths.roundToPlace(newValue, 3));
			}
		});
		sliderBrightnessBoost.addChangeListener(new ScreenListener() {
			@Override
			public void eventOccurred() {
				FlounderShadows.get().setBrightnessBoost(sliderBrightnessBoost.getProgress());
			}
		});

		// Slider Shadowmap Size.
		GuiSliderText sliderShadowSize = new GuiSliderText(paneRight, new Vector2f(0.75f, 0.20f), "Shadowmap Size: ", 512.0f, FlounderFBOs.get().getMaxFBOSize(), FlounderShadows.get().getShadowSize(), GuiAlign.CENTRE);
		FlounderEvents.get().addEvent(new EventChange<Integer>(FlounderShadows.get()::getShadowSize) {
			@Override
			public void onEvent(Integer newValue) {
				sliderShadowSize.setText("Shadowmap Size: " + newValue);
			}
		});
		sliderShadowSize.addChangeListener(new ScreenListener() {
			@Override
			public void eventOccurred() {
				FlounderShadows.get().setShadowSize((int) sliderShadowSize.getProgress());
			}
		});

		// Slider Shadowmap PCFs.
		GuiSliderText sliderShadowPCFs = new GuiSliderText(paneRight, new Vector2f(0.75f, 0.27f), "Shadow PCF Count: ", 0.0f, 8.0f, FlounderShadows.get().getShadowPCF(), GuiAlign.CENTRE);
		FlounderEvents.get().addEvent(new EventChange<Integer>(FlounderShadows.get()::getShadowPCF) {
			@Override
			public void onEvent(Integer newValue) {
				sliderShadowPCFs.setText("Shadow PCF Count: " + newValue);
			}
		});
		sliderShadowPCFs.addChangeListener(new ScreenListener() {
			@Override
			public void eventOccurred() {
				FlounderShadows.get().setShadowPCF((int) sliderShadowPCFs.getProgress());
			}
		});

		// Slider Shadowmap Darkness.
		GuiSliderText sliderShadowDarkness = new GuiSliderText(paneRight, new Vector2f(0.75f, 0.34f), "Shadow Darkness: ", 0.0f, 1.0f, FlounderShadows.get().getShadowDarkness(), GuiAlign.CENTRE);
		FlounderEvents.get().addEvent(new EventChange<Float>(FlounderShadows.get()::getShadowDarkness) {
			@Override
			public void onEvent(Float newValue) {
				sliderShadowDarkness.setText("Shadow Darkness: " + Maths.roundToPlace(newValue, 2));
			}
		});
		sliderShadowDarkness.addChangeListener(new ScreenListener() {
			@Override
			public void eventOccurred() {
				FlounderShadows.get().setShadowDarkness(sliderShadowDarkness.getProgress());
			}
		});

		// Back.
		GuiButtonText back = new GuiButtonText(this, new Vector2f(0.5f, 0.9f), "Back", GuiAlign.CENTRE);
		back.addLeftListener(new ScreenListener() {
			@Override
			public void eventOccurred() {
				slider.setNewSecondaryScreen(settings);
			}
		});
	}

	@Override
	public void updateObject() {
	}

	@Override
	public void deleteObject() {

	}
}
