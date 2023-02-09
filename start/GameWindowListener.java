import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;

import com.mojang.minecraft.Minecraft;

public final class GameWindowListener extends WindowAdapter
{
	final Minecraft mc;
	final Thread thread;

	public GameWindowListener(Minecraft mc, Thread thread) {
		this.mc = mc;
		this.thread = thread;
	}

	public void windowClosing(WindowEvent event) {
		try {
			Field isRunning = mc.getClass().getDeclaredField("running");
			isRunning.setAccessible(true);
			isRunning.set(mc, false);
			this.thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
