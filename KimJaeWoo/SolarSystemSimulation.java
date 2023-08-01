package yourpackage

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SolarSystemSimulation extends JPanel {
	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1080;
	private static final int NUM_CELESTIALS = 9;
	private static final double GRAVITATIONAL_CONSTANT = 0.1;
	private static final double TIME_STEP = 0.01;

	private CelestialBody[] bodies;

	private class CelestialBody {
		double mass;
		double distance; // 초기 거리
		double angle; // 초기 각도
		double speed; // 초기 속도
		Color color;
		String name; // 행성 이름

		public CelestialBody(double mass, double distance, double angle, double speed, Color color, String name) {
			this.mass = mass;
			this.distance = distance;
			this.angle = angle;
			this.speed = speed;
			this.color = color;
			this.name = name;
		}
	}

	public SolarSystemSimulation() {
		bodies = new CelestialBody[NUM_CELESTIALS];

		bodies[0] = new CelestialBody(1000, 0, 0, 0, Color.YELLOW, "Sun");
		bodies[1] = new CelestialBody(0.055, 70, 0, 2.7, Color.GRAY, "Mercury");
		bodies[2] = new CelestialBody(0.815, 110, 0, 2.1, Color.ORANGE, "Venus");
		bodies[3] = new CelestialBody(1, 150, 0, 2, Color.BLUE, "Earth");
		bodies[4] = new CelestialBody(0.107, 190, 0, 1.6, Color.RED, "Mars");
		bodies[5] = new CelestialBody(317.8, 260, 0, -1, Color.LIGHT_GRAY, "Jupiter");
		bodies[6] = new CelestialBody(95.2, 320, 0, -0.7, Color.YELLOW, "Saturn");
		bodies[7] = new CelestialBody(14.5, 370, 0, -0.5, Color.CYAN, "Uranus");
		bodies[8] = new CelestialBody(17.1, 420, 0, -0.4, Color.BLUE, "Neptune");
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (CelestialBody body : bodies) {
			int x = (int) Math.round(WIDTH / 2 + body.distance * Math.cos(body.angle));
			int y = (int) Math.round(HEIGHT / 2 + body.distance * Math.sin(body.angle));
			g.setColor(body.color);
			g.fillOval(x, y, 10, 10);

			// 행성 이름을 표시하기 위한 설정
			g.setFont(new Font("Arial", Font.BOLD, 12));
			g.setColor(Color.WHITE);
			g.drawString(body.name, x + 12, y - 12);
		}
	}

	public void updateSimulation() {
		for (int i = 0; i < NUM_CELESTIALS; i++) {
			CelestialBody body = bodies[i];

			double ax = 0;
			double ay = 0;

			for (int j = 0; j < NUM_CELESTIALS; j++) {
				if (i == j)
					continue;

				CelestialBody other = bodies[j];
				double dx = other.distance * Math.cos(other.angle) - body.distance * Math.cos(body.angle);
				double dy = other.distance * Math.sin(other.angle) - body.distance * Math.sin(body.angle);
				double distance = Math.sqrt(dx * dx + dy * dy);
				double force = GRAVITATIONAL_CONSTANT * body.mass * other.mass / (distance * distance);
				double acceleration = force / body.mass;

				ax += acceleration * dx / distance;
				ay += acceleration * dy / distance;
			}

			double k1vx = TIME_STEP * ax;
			double k1vy = TIME_STEP * ay;
			double k2vx = TIME_STEP * (ax + 0.5 * k1vx);
			double k2vy = TIME_STEP * (ay + 0.5 * k1vy);
			double k3vx = TIME_STEP * (ax + 0.5 * k2vx);
			double k3vy = TIME_STEP * (ay + 0.5 * k2vy);
			double k4vx = TIME_STEP * (ax + k3vx);
			double k4vy = TIME_STEP * (ay + k3vy);

			body.speed += (k1vx + 2 * k2vx + 2 * k3vx + k4vx) / 6.0;
			body.angle += body.speed * TIME_STEP;

			// Boundary Check: If the body goes beyond the screen boundaries, wrap it around
			if (body.angle < 0) {
				body.angle += 2 * Math.PI;
			} else if (body.angle > 2 * Math.PI) {
				body.angle -= 2 * Math.PI;
			}
		}

		repaint();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Solar System Simulation");
		SolarSystemSimulation simulation = new SolarSystemSimulation();
		frame.add(simulation);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		Timer timer = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				simulation.updateSimulation();
			}
		});
		timer.start();
	}
}
