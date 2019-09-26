package me.xxfreakdevxx.de.program;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import me.xxfreakdevxx.de.program.components.GFrame;
import me.xxfreakdevxx.de.program.components.menus.LoginMenu;

public class Assistent extends Canvas implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* GFrame */
	public static int windowWidth = 1000;
	public static int windowHeight = 700;
	
	private boolean isRunning = false;
	public static int fps_current = 0;
	public static int fps_maximal = -1;
	public static double tickSpeed = 120;
	private static Random ran = new Random();
	private Thread thread;
	private Camera camera;
	private TextureAtlas textureAtlas;
	private MouseInput mouseInput;
	
	//Manager, Handler, etc.
	public KeyInput keyinput = null;
	public static GFrame frame = null;
	
	static Assistent instance;
	public static Assistent getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		new Assistent();
	}
	
	public Assistent() {
		instance = this;
		setBackground(Color.BLACK);
		frame = new GFrame(windowWidth, windowHeight, "SquareCraft", new LoginMenu(), this);
		start();
	}
	
	public void preInit() {
		keyinput = new KeyInput();
		mouseInput = new MouseInput();
		this.addKeyListener(keyinput);
		this.addMouseListener(mouseInput);
		this.addMouseMotionListener(mouseInput);
		camera = new Camera(0,0);
		textureAtlas = new TextureAtlas();
	}
	public void init() {
		readyToRender = true; //Starte die Render-Funktion		
	}
	public void postInit() {
	}
	
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
		preInit();
		init();
		postInit();		
	}
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	
	@Override
	public void run() {
		/*
		 * GameLoop - Made by Notch
		 */
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = tickSpeed;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int s = 0;
		boolean allowRender = false;
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				//update++;
				delta--;
			}
			if(fps_maximal == -1 ) allowRender = true;
			if(s != fps_maximal || allowRender) {
				render();
				s++;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps_current = s;
				s = 0;
			}
		}
		stop();
	}
	
	public void setGFrameTitle(String title) {
		if(frame != null) frame.setTitle(title);
	}
	public void tick() {
		Assistent.windowWidth = frame.getWidth();
		Assistent.windowHeight = frame.getHeight();
		if(keyinput != null) keyinput.tick();
		frame.canvas.tick();
	}
	private boolean readyToRender = false;
	public void render() {
		if(readyToRender == false) return;
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(240,240,240));
		g.clearRect(0, 0, Assistent.windowWidth, Assistent.windowHeight);
		g.fillRect(0, 0, Assistent.windowWidth, Assistent.windowHeight);
		g.setColor(Color.BLACK);
		
		frame.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}
	
	public static Camera getCamera() {
		return getInstance().camera;
	}
	
	public void mousePressed(MouseEvent e) {
		frame.canvas.mousePressed(e);
	}
	public void mouseMoved(MouseEvent e) {
		frame.canvas.mouseMoved(e);
	}
	
	@SuppressWarnings("deprecation")
	public static String getTimeInString() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar date = sdf.getCalendar();
		Date d = date.getTime();
		return d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
	}
	@SuppressWarnings("deprecation")
	public static String getDateInString() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar date = sdf.getCalendar();
		Date d = date.getTime();
		int day = d.getDate();
		int mon = d.getMonth();
		int year = d.getYear();
		return day+":"+mon+":"+year;
	}
	public static void log(String prefix, String... strings) {
		if(prefix.equals("")) prefix = "Debug";
		for(String s : strings) {
			System.out.println("["+Assistent.getTimeInString()+"]["+prefix+"] "+s);
		}
	}
	public static double randomDouble(double min, double max) {
	    if (min >= max) {
	        throw new IllegalArgumentException("max must be greater than min");
	    }
	    return min + (max - min) * ran.nextDouble();
	}
	public static int randomInteger(int min, int max) {
	    if (min >= max) {
	        throw new IllegalArgumentException("max must be greater than min");
	    }
	    return ran.nextInt((max - min) + 1) + min;
	}
	public static int calculateStringWidth(Font font, String enteredText) {
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		FontMetrics fm = img.getGraphics().getFontMetrics(font);
		int width = fm.stringWidth(enteredText);
		return width;
	}
}
