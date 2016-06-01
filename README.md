# PunKarnBan

Software Specification and Design : **Final Project** 

Project name : **PunKarnBan** (Tapping game)

Google PlayStore link : - 

Git repository link :  https://github.com/devilmustcry/PunKarnBan

<dt>Members :</dt> 
      <dd>1) Kasidit  Phonchareon    5710546151</dd>
      <dd>2) Patchara Pattiyathanee  5710546348</dd>
      <dd>3) Arut     Thanomwattana  5710546437</dd>


#1) Project explanation 
	
		The concept of this game is student doing homework in various subject (Mathematics,Biology, 
	Chemistry, Physics and English).The main purpose is to tap the screen to help student 
	finish homework by increase progress of ProgressBar until full and then the game will 
	switch to another subject and so on.
	
	Abbreviation : WPT stand for “Word per tap” (amount that increase on progress bar once tap)
	
	1.1) Main features :
		1.1.1) Tap the screen to increase progress of each homework task.
		1.1.2) When finish each task you will gain knowledge for upgrade any material in game.
		1.1.3) Hire several recruits for help in auto-clicking.
		1.1.4) Upgrade skill (passive skill) of each subject to increase amount of WPT.
		       The effect of each subject will apply when the student do that subject task.
		1.1.5) Upgrade stationery (like a weapon in RPG) to increase WPT.
	
	1.2) Additional features :
		1.2.1) Every 10 level of tasks student must to do a project that have higher amount of progress 
		       than normal homework tasks. Besides, student gain more knowledge than normal homework 
		       but student have 60 seconds to finish project task. If student can’t finish project 
		       task in time the game will turn back the level by 9.  
		1.2.2) Infinite level of tasks (difficulty increase in exponential rate).
		  
		
					
#2) Design Pattern  
	
	
			The design pattern that use in this project are Singleton, Factory, Strategy, State, Observer 
		and Memento pattern.

	2.1) Singleton pattern
		
	  Class that related to this pattern : 
		
		  1) Models/Game/Game.java
		  2) Models/Utility/DecimalConverter.java
		  3) Models/Utility/FormulaCalculator.java
	        
	  Objective :
		
			1) Game.java will create only one time because Game.java is mediator between 
			   others model class and GUI (MainActivity.java).
			2) DecimalConverter.java  and  FormulaCalculator.java is mathematics base class 
			   so our group decide to make it to singleton pattern to decrease redundancy for 
			   create new object class because every upgrade price depend on equation and have 
			   to calculate by these classes every time when it have changed.


	2.2) Factory pattern
		
	  Class that related to this pattern : 
		
		   1) Models/Work/WorkFactory.java
		   2) Models/Work/HomeworkFactory.java
		   3) Models/Work/ProjectFactory.java
	        
	  Objective :
		
		   1) To make every homework and project task that create by game make it create 			    
			 through factory class not directly create new Homework or Project object.


	2.3) Strategy pattern

	  Class that related to this pattern :
		  
		   1) Models/Skill/SkillManager.java
	         
	  Composite class (in package Models/Skill) :
		   
		   1) Mathematics.java  2) Biology.java 3) Chemistry.java 4) Physics.java 
		   5) English.java 6) Skill.java (super class)

	  Objective :
		   
		   1) According to project explanation , there are several subjects if you 			       	       
		   upgrade passive skill these will apply depend on task subject. We use                   		       
		   Strategy pattern to switch WPT calculation of passive skill.



	2.4) State Pattern

	  Class that related to this pattern :
		    
		    1) Models/State/DefaultState.java
		    2) Models/State/TappingState.java
		    3) Models/State/TapState.java (interface)

		Objective :
		  
		    1) To switch image action when tap and un-tap (image will also change when stationery 
		      have changed) 


	2.5) Observer pattern

		Class that related to this pattern :
		     1) Models/Game/Game.java (Observer)
		     2) Activities/MainActivity.java (Observable)

		Objective :
		     1) Any changes in game class will inform to UI 



	2.6) Memento pattern

		Class that related to this pattern :
		     1) Memento/Memento.java
		
		Association class :
		     1) Models/Game/Game.java
		     2) Models/Player/Player.java
	
		Objective :
		     1) To make game save its state to external memory, the game will not reset when you 
		        re-open it again.


#3) Design Principles 

	3.1) GRASP Principle :
		
		3.1.1) Creator : 
			- Game create Player and Work Factory
			- Player create Stationery
			- Player create Skill Manager
			- Skill Manager create Skill
		 	
		3.1.2) Information Expert :
			- Work Factory have data to create Work
		
		3.1.3) Low Coupling
			- If we want to have new recruit you can add it directly without affect to exist 
			  recruits.
			- You can add new Passive Skill directly without affect to exist skill. 
		
		3.1.4) High Cohesion
			- Stationery , Work Factory and every class in utility package
		
		3.1.5) Controller
			- Game control everything that happen in game.
		
		3.1.6) Polymorphism
			- Work , Work Factory , Skill , Recruit , TapState , Memento all are abstract class or 
			  interface.
		
		3.1.7) Pure Fabrication
			- Skill Manager are reusable class.
		
		3.1.8) Indirection
			- All Observer pattern that apply in this project (UI and Game).
		
		3.1.9) Protect Variation
			- UI will have interact with Game only.
