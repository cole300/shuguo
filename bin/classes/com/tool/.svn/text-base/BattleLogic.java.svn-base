package com.tool;

import java.util.LinkedList;

import com.shu.ShuguoActivity;
import com.view.Battle;
import com.view.BattleBoard;
import com.view.WuJiang;


/*战斗的<逻辑层><显示层>*/
public class BattleLogic {

	public static BattleLogic instance;
	public static BattleLogic getInstance()
	{
		if(instance == null)
		{
			instance = new BattleLogic();
		}
		
		return instance;
	}
	
	public static final int HomeTeam = 0;
	public static final int GuestTeam = 1;
	
	/*驱动常量*/
	public static final int AniDrive_Info = 0;
	public static final int AniDrive_AttackTeam = 1;
	public static final int AniDrive_TargetTeam = 2;
	public static final int AniDrive_AttackerId = 3;
	public static final int AniDrive_TargetId = 4;
	public static final int AniDrive_AttackValue = 5;
	
	/*显示层容器*/
	private LinkedList<LinkedList<String>> vecContent = new LinkedList<LinkedList<String>>();
	/*主队容器*/
	private LinkedList<com.data.WuJiang> vecHome = new LinkedList<com.data.WuJiang>(); 
	/*客队容器*/
	private LinkedList<com.data.WuJiang> vecGuest = new LinkedList<com.data.WuJiang>();
	//target集合
	private LinkedList<Integer> vecTarget = new LinkedList<Integer>();
	//战报解析索引
	private int idxDrive;
	//驱动锁
	private boolean lockDrive = false;
	
	public void driveCheck(ShuguoActivity activity)
	{
		if(this.idxDrive >= this.vecContent.size() -1)
		{
			this.setLockDrive(true);
			System.out.println("stop at analysis :"+this.vecContent.get(idxDrive).get(AniDrive_Info));
			Battle.visInfoBar = false;
			WarnningManager.getInstance().removeAll();
			BattleBoard wj = new BattleBoard(activity.getApplicationContext(), activity);
			activity.group.addView(wj);
		}
	}
	public void driveNext()
	{
		this.idxDrive ++;
	}
	public int getIdxDrive()
	{
		return this.idxDrive;
	}
	public void setLockDrive(boolean isLock)
	{
		this.lockDrive = isLock;
	}
	public boolean getLockDrive()
	{
		return this.lockDrive;
	}
	/*初始化驱动*/
	public void format()
	{
		this.idxDrive = 0;
	}
	
	/*获取战斗过程内容*/
	public LinkedList<LinkedList<String>> getContent()
	{
		return this.vecContent;
	}
	/*序列化转换数据*/
	public void serializeData(LinkedList<com.element.EleBattle> eleGroup_home, LinkedList<com.element.EleBattle> eleGroup_guest)
	{
		/*序列化转换前初始化主&客队容器*/
		this.vecHome.clear();
		this.vecGuest.clear();
		/*序列化转换*/
		for(int i = 0; i < eleGroup_home.size(); i ++)
		{
			this.vecHome.add((eleGroup_home.get(i)).getWuJiang());
		}
		for(int i = 0; i < eleGroup_guest.size(); i ++)
		{
			this.vecGuest.add((eleGroup_guest.get(i)).getWuJiang());
		}
	}
	/*计算结果*/
	public void simulateResult()
	{
//		System.out.println("====  Battle Gegin!   =====");
		while(true)
		{
			/*轮次攻击*/
			//主队攻击
			for(int i = 0; i < this.vecHome.size(); i ++)
			{
				//跳过已阵亡的武将
				if(this.vecHome.get(i).getHp() <= 0)
				{
//					System.out.println("主队跳过武将："+this.vecHome.get(i).getName());
					continue;
				}
				//选择对面一个没有阵亡的武将进攻
//				System.out.println("主队进攻者："+vecHome.get(i).getName());
				this.attackFilter(0, this.vecHome.get(i), i, this.vecGuest);
			}
//			System.out.println("主队进攻完成");
			//客队全部阵亡
			if(this.isTeamDead(this.vecGuest))
			{
				LinkedList<String> tempVec = new LinkedList<String>();
								   tempVec.add("客队全部阵亡");
				this.vecContent.add(tempVec);
//				System.out.println("客队全部阵亡！GAME OVER");
				break;
			}
			
			//客队进攻
			for(int i = 0; i < this.vecGuest.size(); i ++)
			{
				//跳过已阵亡的武将
				if(this.vecGuest.get(i).getHp() <= 0)
					continue;
				//选择对面一个没有阵亡的武将进攻
//				System.out.println("客队进攻者："+vecGuest.get(i).getName());
				this.attackFilter(1, this.vecGuest.get(i), i, this.vecHome);
			}
//			System.out.println("客队进攻完成");
			//主队全部阵亡
			if(this.isTeamDead(this.vecHome))
			{
				LinkedList<String> vecTemp = new LinkedList<String>();
								   vecTemp.add("主队全部阵亡");
				this.vecContent.add(vecTemp);
//				System.out.println("主队全部阵亡 GAME OVER");
				break;
			}
		}
	}
	/*随机攻击一个存活目标*/
	public void attackFilter(int team, com.data.WuJiang attacker, int attackerId, LinkedList<com.data.WuJiang> vecTarget)
	{
		this.vecTarget.clear();
		for(int i = 0 ; i < vecTarget.size(); i ++)
		{
//			System.out.println("攻击目标检测：" + vecTarget.get(i).getName() + " hp:"+vecTarget.get(i).getHp());
			if(vecTarget.get(i).getHp() <= 0)
				continue;
			this.vecTarget.add(i);
		}
		if(this.vecTarget.size() == 0)
			return;
		//随机一个打击目标
		int targetId = Tool.getInstance().rand(this.vecTarget.size());
		int damage = attacker.getAttack() / 5 + Tool.getInstance().rand(5);
		
		LinkedList<String> tempVec = new LinkedList<String>();
						   //格式：[我方] 夏侯渊 命中 [敌方] 关兴 造成 33 点伤害
						   tempVec.add(Tool.getInstance().getTeamName(team)+attacker.getName()+"出阵，砍伤对方"+vecTarget.get(this.vecTarget.get(targetId)).getName()+", 血量－"+damage);
						   tempVec.add(String.valueOf(team));//attack team
						   tempVec.add(String.valueOf(Tool.getInstance().getReverseTeam(team)));//target team
						   tempVec.add(String.valueOf(attackerId));//attackerId
						   tempVec.add(String.valueOf(this.vecTarget.get(targetId)));//targetId
						   tempVec.add(String.valueOf(damage));//attackValue
		this.vecContent.add(tempVec);
		//扣血<attacker攻击>
		vecTarget.get(this.vecTarget.get(targetId)).setHp(vecTarget.get(this.vecTarget.get(targetId)).getHp() - damage);
	}
	/*判断是否阵亡*/
	public boolean isTeamDead(LinkedList<com.data.WuJiang> vecWJ)
	{
		for(int i = 0; i < vecWJ.size(); i ++)
		{
			if(vecWJ.get(i).getHp() > 0)
				return false;
		}
		
		return true;
	}
}
