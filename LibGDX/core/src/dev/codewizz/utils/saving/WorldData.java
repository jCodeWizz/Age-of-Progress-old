package dev.codewizz.utils.saving;

import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.Gdx;

import dev.codewizz.utils.serialization.RCDatabase;
import dev.codewizz.utils.serialization.RCField;
import dev.codewizz.utils.serialization.RCObject;
import dev.codewizz.utils.serialization.RCString;
import dev.codewizz.world.Cell;
import dev.codewizz.world.GameObject;
import dev.codewizz.world.Serializable;
import dev.codewizz.world.TileType;
import dev.codewizz.world.World;
import dev.codewizz.world.pathfinding.CellGraph;
import dev.codewizz.world.settlement.Settlement;

public class WorldData {

	public Cell[][] grid;
	public CellGraph cellGraph;
	public Settlement settlement;
	public List<GameObject> objects;
	public boolean showStartInfo;
	public TileType[] tileTypes;

	public static WorldData load(RCDatabase db) {
		
		WorldData data = new WorldData();

		RCObject generalData = db.findObject("generalData");
		data.showStartInfo = generalData.findField("showStartInfo").getBoolean();

		RCObject settlementData = db.findObject("settlementData");
		Settlement settlement = new Settlement(settlementData.findField("x").getFloat(),
				settlementData.findField("y").getFloat());
		data.settlement = settlement;

		RCObject tiles = db.findObject("tiles");
		int size = World.WORLD_SIZE_H * World.WORLD_SIZE_W;
		TileType[] tileTypes = new TileType[size];

		for (int i = 0; i < tileTypes.length; i++) {
			tileTypes[i] = TileType.valueOf(tiles.findString("" + i).getString());
		}

		data.grid = new Cell[World.WORLD_SIZE_W][World.WORLD_SIZE_H];
		data.cellGraph = new CellGraph();
		data.tileTypes = tileTypes;
		data.objects = new CopyOnWriteArrayList<GameObject>();

		for (int i = 0; i < data.grid.length; i++) {
			for (int j = 0; j < data.grid[i].length; j++) {
				if (j % 2 == 0) {
					Cell cell = new Cell((i - (World.WORLD_SIZE_W / 2)) * 64, (j - (World.WORLD_SIZE_H / 2)) * 16, i, j,
							false);
					data.grid[i][j] = cell;
					data.cellGraph.addCell(cell);
				} else {
					Cell cell = new Cell((i - (World.WORLD_SIZE_W / 2)) * 64 + 32, (j - (World.WORLD_SIZE_H / 2)) * 16,
							i, j, true);
					data.grid[i][j] = cell;
					data.cellGraph.addCell(cell);
				}
			}
		}


		return data;
	}

	public static void save(World world, String path) {
		File file = Gdx.files.internal(path).file();
		RCDatabase db = new RCDatabase("worldname");

		RCObject generalData = new RCObject("generalData");
		generalData.addField(RCField.Boolean("showStartInfo", world.showInfoSartMenu));
		db.addObject(generalData);

		RCObject settlementData = new RCObject("settlementData");
		settlementData.addField(RCField.Float("x", world.settlement.getX()));
		settlementData.addField(RCField.Float("y", world.settlement.getY()));
		db.addObject(settlementData);

		RCObject tiles = new RCObject("tiles");

		for (int i = 0; i < world.grid.length; i++) {
			for (int j = 0; j < world.grid[i].length; j++) {
				tiles.addString(RCString.Create("" + (int) (i + (j * World.WORLD_SIZE_W)),
						world.grid[i][j].tile.getType().toString()));
			}
		}
		
		db.addObject(tiles);

		for (GameObject object : world.objects) {
			if (object instanceof Serializable) {
				RCObject ro = new RCObject("$" + object.getID());

				ro.addField(RCField.Float("x", object.getX()));
				ro.addField(RCField.Float("y", object.getY()));
				
				ro = ((Serializable) object).save(ro);

				db.addObject(ro);
			}
		}

		db.serializeToFile(file);
	}
}
