package cot.qin.sdm;

public class BasicBlock {
	private int type;
	private String[] typeImage = { "gress.png", "rocks.png", "road_x.png",
			"road_y.png", "road_cross.png", "corner_lu.png", "corner_ld.png",
			"corner_ru.png", "corner_rd.png", "road_lb.png", "road_rb.png",
			"road_ub.png", "road_db.png", "bridge_x.png", "bridge_y.png",
			"river_x.png", "river_y.png", "river_u.png", "river_l.png",
			"river_d.png", "river_r.png" };

	public BasicBlock(int set_type) {
		// TODO Auto-generated constructor stub
		type = set_type;
	}

	public String getTypeImage() {
		return ("fig/" + typeImage[type]);
	}

	public int getType() {
		return type;
	}
}
