package edu.KorobovMS;

import org.opencv.core.Mat;
import org.opencv.highgui.*;
import java.util.*;
import edu.KorobovMS.OpenCVExtensions.*;

import org.slf4j.*;

/**
 * Структуры и алгоритмы обработки данных
 * Лабораторная работа #6
 * Основной класс
 */
class Lab6 {
	private static final Logger logger;
	
	static {
		// Инициализируем логгер
		logger = LoggerFactory.getLogger(Lab6.class);
		if (logger == null) {
			throw new RuntimeException();
		}
		
		logger.info("Logger configured!");
		
		// Подгружаем библиотеку для работы с графикой
		System.loadLibrary("opencv_java245");
	}
	
	public static void main(String[] args) {
		Mat source = Highgui.imread("..\\source.png");
		if (source == null) {
			logger.error("Can't open source");
		}
		double[] src = {255.0, 0.0, 0.0};
		double[] dest = {0.0, 255.0, 0.0};
		double[] bkgr = {255.0, 255.0, 255.0};
		double[] bdr = {0.0, 0.0, 0.0};
		LeeAlgorythm alg = new LeeAlgorythm(source, src, dest, bkgr, bdr);
		List<Point> points = alg.findPath();
		int pSize = points.size();
		for (int i = 0; i < pSize; i++) {
			Point cur = points.get(i);
			source.put(cur.getRow(), cur.getCol(), 0.0, 0.0, 255.0);
		}
		Highgui.imwrite("..\\dest.png", source);
	}
}
