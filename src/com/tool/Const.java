package com.tool;

/*Constants*/
public class Const {

	public static final int SCREEN_W = 800;
	public static final int SCREEN_H = 480;
	
	/*tile size*/
	public static final int TILE_W = 32;//64/2
	public static final int TILE_H = 16;//64/4
	
	/*anchor*/
	public static final int ANCHOR_NORMAL = 0;//
	public static final int ANCHOR_MIRROR = 1;//����
	
	/*equip index*/
	public static final int EQUIP_ARMOR =  0;//ARMOR  ����
	public static final int EQUIP_WEAPON = 1;//WEAPON ����
	public static final int EQUIP_HORSE =  2;//HORSE  ����
	public static final int EQUIP_HELMET = 3;//HELMET ͷ��
	
	/*��������Building*/
	public static final int Building_Dating =     0;
	public static final int Building_Bingying =   1;
	public static final int Building_Tiejiangpu = 2;
	public static final int Building_Jiuguan =    3;
	
	/*guge*/
	public static final int undifined = - 999;
	/*�ڵ���Ϣ����*/
	public static final int NODE_INFO_ID = 0;
	public static final int NODE_INFO_FATHER_X = 1;
	public static final int NODE_INFO_FATHER_Y = 2;
	public static final int NODE_INFO_CHILD_X  = 3;
	public static final int NODE_INFO_CHILD_Y  = 4;
	/*֡��Ϣ����*/
	public static final int FRAMEDATA_X = 0;
	public static final int FRAMEDATA_Y = 1;
	public static final int FRAMEDATA_DEGREE = 2;
	public static final int FRAMEDATA_X_ORIGIN = 3;
	public static final int FRAMEDATA_Y_ORIGIN = 4;
	public static final int FRAMEDATA_BMP_ID = 5;
	/*ս����ɫ�ƶ�״̬*/
	public static final byte EleBattle_Move = 0;//�ƶ��׶�
	public static final byte EleBattle_Rush = 1;//��̽׶�
	public static final byte EleBattle_Battle = 2;//ս���׶�
	/*ս���ִν������*/
	public static final String BATTLE_NEXT_ROUND_EFFNAME = "eff_blood";
	
	/*mapId*/
	public static final int ID_MAP_Map = 1;//���ͼ
	public static final int ID_MAP_DaTing = 50;//����
	public static final int ID_MAP_RenMing = 51;//����
}
