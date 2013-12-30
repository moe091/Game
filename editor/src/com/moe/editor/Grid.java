package com.moe.editor;

public class Grid {
float size = 0.2f;


	public Grid(float size) {
		this.size = size;
	}
	
	public float round(float num) {
		if (size < 1) {
			int multiplier = (int) (1 / size);
			
			num = num * (float) multiplier;
			num = (int) num;
			num = num / (float) multiplier;
			return num;
		} else if (size == 1) {
			num = (int) num;
			return num;
		} else {
			return Math.round(num / size) * size;
		}
	}
}
