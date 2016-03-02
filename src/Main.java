

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	private int size = 0;
	private Map<Integer, ByteBuffer> cache = new HashMap<>();

	int garbageSize = 1024 * 32;
	int cycles = 10;
	int objects = 1024;
	private ByteBuffer[] garbage = null;

	public Main(int cacheSizeArg) {
		size = cacheSizeArg * 1024 * 1024;
	}

	private int createGarbage(int key) {
		garbage = new ByteBuffer[1024];
		for (int j = 0; j < cycles; j++) {
			for (int i = 0; i < objects; i++) {
				garbage[i++] = ByteBuffer.allocate(garbageSize);
				cache.put(i, ByteBuffer.allocateDirect(4));
			}
		}
		return garbage.length;
	}

	private void run(int runTimeInMinutes) {
		System.out.println("size=" + size);
		int key = 0;
		while (key < size) {
			cache.put(key++, ByteBuffer.allocateDirect(4));
			if (key % 1000000 == 0)
				System.out.println("key=" + key);
		}
		System.out.println("Filled direct byte buffers=" + size);
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() < (start + runTimeInMinutes * 60 * 1000)) {
			createGarbage(key++);
			if (key % 100 == 0)
				System.out.println("key=" + key);
		}
	}

	public static void main(String[] args) {
		int cacheSizeInMillion = 10;
		int runTimeInMinutes = 5;
		new Main(cacheSizeInMillion).run(runTimeInMinutes);
	}

}
