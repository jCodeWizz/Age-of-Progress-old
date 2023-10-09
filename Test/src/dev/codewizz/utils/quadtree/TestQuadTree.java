package dev.codewizz.utils.quadtree;

public class TestQuadTree {

	public static void main(String[] args) {
		
		QuadTree<String> tree = new QuadTree<String>(0, 0, 400, 400);
		
		for(int i = 0; i < 400; i++) {
			for(int j = 0; j < 400; j++) {
				tree.set(i, j, "string_" + i + "_" + j);
			}
		}
		
		Point<String>[] list = tree.searchWithin(10, 10, 30, 30);
		
		for(int i = 0; i < list.length; i++) {
			System.out.println(list[i].getValue());
		}
		
		
	}
}
