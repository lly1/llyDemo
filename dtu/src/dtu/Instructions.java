package dtu;

public class Instructions {
	/* 艾伏逆变器的指令 */
	// AP 询问逆变器是否被注册 XW+校验和
	public static final String XW = "3A 3A 01 00 00 00 10 00 00";
	// AP分配给逆变器的地址编号 ZC+逆变器设备号+分配地址+校验和
	public static final String ZC = "3A 3A 01 00 00 00 10 01 11";
	// AP读逆变器常规信息 READ+校验和
	public static final String READ = "3A 3A 01 00 00 ff 11 02 00";
}
