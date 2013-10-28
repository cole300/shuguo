package com.tool;

import java.util.LinkedList;

import com.shu.ShuguoActivity;
import com.view.Battle;
import com.view.BattleBoard;
import com.view.WuJiang;


/*ս����<�߼���><��ʾ��>*/
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
	
	/*��������*/
	public static final int AniDrive_Info = 0;
	public static final int AniDrive_AttackTeam = 1;
	public static final int AniDrive_TargetTeam = 2;
	public static final int AniDrive_AttackerId = 3;
	public static final int AniDrive_TargetId = 4;
	public static final int AniDrive_AttackValue = 5;
	
	/*��ʾ������*/
	private LinkedList<LinkedList<String>> vecContent = new LinkedList<LinkedList<String>>();
	/*��������*/
	private LinkedList<com.data.WuJiang> vecHome = new LinkedList<com.data.WuJiang>(); 
	/*�Ͷ�����*/
	private LinkedList<com.data.WuJiang> vecGuest = new LinkedList<com.data.WuJiang>();
	//target����
	private LinkedList<Integer> vecTarget = new LinkedList<Integer>();
	//ս����������
	private int idxDrive;
	//������
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
	/*��ʼ������*/
	public void format()
	{
		this.idxDrive = 0;
	}
	
	/*��ȡս����������*/
	public LinkedList<LinkedList<String>> getContent()
	{
		return this.vecContent;
	}
	/*���л�ת������*/
	public void serializeData(LinkedList<com.element.EleBattle> eleGroup_home, LinkedList<com.element.EleBattle> eleGroup_guest)
	{
		/*���л�ת��ǰ��ʼ����&�Ͷ�����*/
		this.vecHome.clear();
		this.vecGuest.clear();
		/*���л�ת��*/
		for(int i = 0; i < eleGroup_home.size(); i ++)
		{
			this.vecHome.add((eleGroup_home.get(i)).getWuJiang());
		}
		for(int i = 0; i < eleGroup_guest.size(); i ++)
		{
			this.vecGuest.add((eleGroup_guest.get(i)).getWuJiang());
		}
	}
	/*������*/
	public void simulateResult()
	{
//		System.out.println("====  Battle Gegin!   =====");
		while(true)
		{
			/*�ִι���*/
			//���ӹ���
			for(int i = 0; i < this.vecHome.size(); i ++)
			{
				//�������������佫
				if(this.vecHome.get(i).getHp() <= 0)
				{
//					System.out.println("���������佫��"+this.vecHome.get(i).getName());
					continue;
				}
				//ѡ�����һ��û���������佫����
//				System.out.println("���ӽ����ߣ�"+vecHome.get(i).getName());
				this.attackFilter(0, this.vecHome.get(i), i, this.vecGuest);
			}
//			System.out.println("���ӽ������");
			//�Ͷ�ȫ������
			if(this.isTeamDead(this.vecGuest))
			{
				LinkedList<String> tempVec = new LinkedList<String>();
								   tempVec.add("�Ͷ�ȫ������");
				this.vecContent.add(tempVec);
//				System.out.println("�Ͷ�ȫ��������GAME OVER");
				break;
			}
			
			//�Ͷӽ���
			for(int i = 0; i < this.vecGuest.size(); i ++)
			{
				//�������������佫
				if(this.vecGuest.get(i).getHp() <= 0)
					continue;
				//ѡ�����һ��û���������佫����
//				System.out.println("�Ͷӽ����ߣ�"+vecGuest.get(i).getName());
				this.attackFilter(1, this.vecGuest.get(i), i, this.vecHome);
			}
//			System.out.println("�Ͷӽ������");
			//����ȫ������
			if(this.isTeamDead(this.vecHome))
			{
				LinkedList<String> vecTemp = new LinkedList<String>();
								   vecTemp.add("����ȫ������");
				this.vecContent.add(vecTemp);
//				System.out.println("����ȫ������ GAME OVER");
				break;
			}
		}
	}
	/*�������һ�����Ŀ��*/
	public void attackFilter(int team, com.data.WuJiang attacker, int attackerId, LinkedList<com.data.WuJiang> vecTarget)
	{
		this.vecTarget.clear();
		for(int i = 0 ; i < vecTarget.size(); i ++)
		{
//			System.out.println("����Ŀ���⣺" + vecTarget.get(i).getName() + " hp:"+vecTarget.get(i).getHp());
			if(vecTarget.get(i).getHp() <= 0)
				continue;
			this.vecTarget.add(i);
		}
		if(this.vecTarget.size() == 0)
			return;
		//���һ�����Ŀ��
		int targetId = Tool.getInstance().rand(this.vecTarget.size());
		int damage = attacker.getAttack() / 5 + Tool.getInstance().rand(5);
		
		LinkedList<String> tempVec = new LinkedList<String>();
						   //��ʽ��[�ҷ�] �ĺ�Ԩ ���� [�з�] ���� ��� 33 ���˺�
						   tempVec.add(Tool.getInstance().getTeamName(team)+attacker.getName()+"���󣬿��˶Է�"+vecTarget.get(this.vecTarget.get(targetId)).getName()+", Ѫ����"+damage);
						   tempVec.add(String.valueOf(team));//attack team
						   tempVec.add(String.valueOf(Tool.getInstance().getReverseTeam(team)));//target team
						   tempVec.add(String.valueOf(attackerId));//attackerId
						   tempVec.add(String.valueOf(this.vecTarget.get(targetId)));//targetId
						   tempVec.add(String.valueOf(damage));//attackValue
		this.vecContent.add(tempVec);
		//��Ѫ<attacker����>
		vecTarget.get(this.vecTarget.get(targetId)).setHp(vecTarget.get(this.vecTarget.get(targetId)).getHp() - damage);
	}
	/*�ж��Ƿ�����*/
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
