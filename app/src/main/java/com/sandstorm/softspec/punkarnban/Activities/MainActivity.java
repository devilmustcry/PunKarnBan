package com.sandstorm.softspec.punkarnban.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sandstorm.softspec.punkarnban.Adapters.PagerAdapter;
import com.sandstorm.softspec.punkarnban.Graphic.TapImage;
import com.sandstorm.softspec.punkarnban.Models.Game.Game;
import com.sandstorm.softspec.punkarnban.Models.Player.Player;
import com.sandstorm.softspec.punkarnban.Models.Recruit.Recruit;
import com.sandstorm.softspec.punkarnban.Models.Works.Homework;
import com.sandstorm.softspec.punkarnban.Models.Works.Project;
import com.sandstorm.softspec.punkarnban.Models.Works.Work;
import com.sandstorm.softspec.punkarnban.R;
import com.sandstorm.softspec.punkarnban.Graphic.DamagePool;
import com.sandstorm.softspec.punkarnban.Graphic.HealthBar;
import com.sandstorm.softspec.punkarnban.State.DefualtState;
import com.sandstorm.softspec.punkarnban.State.TapState;
import com.sandstorm.softspec.punkarnban.Utility.DecimalConverter;
import com.sandstorm.softspec.punkarnban.Utility.FileReader;
import com.sandstorm.softspec.punkarnban.Utility.FileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity implements Observer{

    /**
     * ImageView for main charactor (The one that you tap)
     */
    private TapImage tap;

    private boolean projectSet = false;

    /**
     * TabLayout for upgradable
     */

    private TabLayout upgradeTab;

    /**
     * ViewPager for upgradable detail
     */

    private ViewPager upgradeDetail;

    /**
     * adapter for view pager
     */

    private PagerAdapter adapter;

    /**
     * HealthBar is a ProcessBar decorate with textview (name and hp)
     */

    private HealthBar healthBar;

    /**
     * Game is controller of this game
     */

    private Game game;

    /**
     * knowledgePoint show in game
     */
    private TextView knowledgePoint;

    /**
     * Level present in game
     */
    private TextView level;

    /**
     * Layout for set background
     */
    private RelativeLayout background;

    /**
     * State of tap image
     */
    private TapState state;
    /**
     * Damage pool(List of damage text)
     */
    private DamagePool damagePool ; // damage pool (graphic reason)

    /**
     *
     */
    private TextView totalRecruitDamageText;

    /**
     *
     */
    private TabLayout.TabLayoutOnPageChangeListener pageChangeListener;

    /**
     *
     */
    private List<ImageView> recruitImage;

    /**
     * Create an activity
     * @param savedInstanceState : Don't know what is it....
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initComponents();

    }

    /**
     * init Component in activity
     */
    private void initComponents() {

        game = Game.getInstance(); //get instance from game
        game.addObserver(this); // add this as an obsever to observe game

        restart();

        Log.i("Game Level", game.getPresentLevel() + "");

        background = (RelativeLayout) findViewById(R.id.relative);

        tap = (TapImage) findViewById(R.id.tap); //get image from view

        state = new DefualtState(this,tap);

        healthBar = (HealthBar) findViewById(R.id.work_health_bar);
        game.initWork();
        healthBar.setNameText(game.getWork().getName());
        healthBar.setProgress(game.getCurrentProcess());
        healthBar.setHealthText(game.getCurrentProcess() + "/" + game.getWork().getHp());
        healthBar.setMax(game.getWork().getHp());

        knowledgePoint = (TextView) findViewById(R.id.player_knowledge);
        knowledgePoint.setText(game.getPlayer().getKnowledge() + "");

        level = (TextView) findViewById(R.id.game_level);
        level.setText(game.getPresentLevel() + "");

        totalRecruitDamageText = (TextView) findViewById(R.id.recruit_total);


        tap.setOnClickListener(new View.OnClickListener() {// set onclick listener for image
            @Override
            public void onClick(View v) {
                changeImage();//change image when tap
                int damage = game.tap();//invoke game tap and return the damage
//                if (damagePool == null) {
//                    damagePool = new DamagePool(getApplicationContext(), background);
//                    damagePool.start();
//                }
//                damagePool.addPool(getApplicationContext(), background, damage);//add into damagePool

            }
        });

        //Initialize upgradeTab
        upgradeTab = (TabLayout) findViewById(R.id.upgrade_layout);
        upgradeTab.addTab(upgradeTab.newTab().setText("Stationery"));
        upgradeTab.addTab(upgradeTab.newTab().setText("Recruit"));
        upgradeTab.addTab(upgradeTab.newTab().setText("Skill"));
        upgradeTab.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initialize upgradeDetail and adapter
        upgradeDetail = (ViewPager) findViewById(R.id.upgrade_detail);
        adapter = new PagerAdapter(getSupportFragmentManager(), upgradeTab.getTabCount() );
        upgradeDetail.setAdapter(adapter);

        //addOnPageChangeListener and setOnTabSelectedListener
         pageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(upgradeTab);
        upgradeDetail.addOnPageChangeListener(pageChangeListener);
        upgradeTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                upgradeDetail.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //Find Healthbar from view


        setBackground();

        setRecruitImage();


        if(game.getRecruitDamage()!=0) {
            game.startRecruitTimer();
            totalRecruitDamageText.setText(game.getRecruitDamage() + "");
            setRecruitPresentImage();
        }
    }

    private void setRecruitPresentImage() {
        for(int i = 0;i<game.getRecruits().size();i++) {
            if(game.getRecruits().get(i).getLevel()!=0) {
                final int index = i;
                recruitImage.get(i).post(new Runnable() {
                    @Override
                    public void run() {
                        recruitImage.get(index).setVisibility(View.VISIBLE);

                    }
                });
            }
        }

    }

    private void setRecruitImage() {

        recruitImage = new ArrayList<ImageView>();
        recruitImage.add((ImageView) findViewById(R.id.recruit_1));
        recruitImage.add((ImageView) findViewById(R.id.recruit_2));
        recruitImage.add((ImageView) findViewById(R.id.recruit_3));
        recruitImage.add((ImageView) findViewById(R.id.recruit_4));
        recruitImage.add((ImageView) findViewById(R.id.recruit_5));
        recruitImage.add((ImageView) findViewById(R.id.recruit_6));

    }

    private void setBackground() {


        background.post(new Runnable() {
            @Override
            public void run() {

                int resource = 0;
                switch (game.getWork().getName()) {
                    case "Mathematics":
                        resource = R.drawable.math_bg;
                        break;
                    case "Chemistry":
                        resource = R.drawable.chem_bg;
                        break;
                    case "Physics":
                        resource = R.drawable.phy_bg;
                        break;
                    case "English":
                        resource = R.drawable.english_bg;
                        break;
                    case "Biology":
                        resource = R.drawable.bio_bg;

                }
                background.setBackground(getDrawable(resource));

            }
        });

    }

    /**
     * Change image method
     */
    private void changeImage() {
        state.tap();
    }


    /**
     * Update method call when observable call notifysetchanged()
     * @param observable : call from which observable
     * @param data : data sent from observable
     */
    @Override
    public void update(Observable observable, Object data) {


        if(data == null) return;

        if(data.getClass() == Homework.class ) {

            Work work = (Work) data;
            setAllText(work);
            projectSet = false;
            tap.setTime("");
            tap.postInvalidate();
            setBackground();
        }
        else if(data.getClass() == Project.class) {
            Project work = (Project) data;
            if(!projectSet) {
                setAllText(work);
                setBackground();
                projectSet = true;

            }
            tap.setTime(work.getTime() + "");
            tap.postInvalidate();
        }

        if(data.getClass() == Integer.class) {

            int process = (int) data;
            healthBar.setProgress(process);
            healthBar.setHealthText(process + "/" + healthBar.getText().split("/")[1]);
        }

        if(data.getClass() == Player.class) {
            final Player player = (Player) data;
            knowledgePoint.post(new Runnable() {
                @Override
                public void run() {
                    knowledgePoint.setText(DecimalConverter.getInstance().convert(player.getKnowledge()));
                }
            });

        }

        if(data.getClass() == String.class) {
            String totalDamage = (String) data;

            totalRecruitDamageText.setText(totalDamage);
            setRecruitPresentImage();
        }

    }



    private void setAllText(Work work) {
        healthBar.setProgress(0);
        healthBar.setHealthText("0/" + work.getHp());
        healthBar.setNameText(work.getName());
        healthBar.setMax(work.getHp());
        level.post(new Runnable() {
            @Override
            public void run() {
                level.setText(game.getPresentLevel() + "");
            }
        });
    }

    public void setState(TapState state) {
        this.state = state;
    }

    public MainActivity getActivity() {
        return this;
    }

    public ImageView getTapImage() {
        return tap;
    }

    //---------------------------------------------------------Save/Load Code


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Stop", "Call onStop");
        FileWriter.write(game.saveState(),getApplicationContext());
        FileWriter.write(game.getPlayer().saveState(),getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.stopTimer();
        upgradeTab.removeAllTabs();
        upgradeDetail.removeOnPageChangeListener(pageChangeListener);
    }

    //    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.i("Destroy", "Call onDestroy");
//
//        FileWriter.write(game.saveState());
//
//    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Resume", "Call onResume");

        initComponents();

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//           initComponents();
//
//    }

    public void restart() {
//        game.restore(FileReader.readGame());
//        game.getPlayer().restore(FileReader.readPlayer());
        game.restore(FileReader.readMemento("game",getApplicationContext()));
        game.getPlayer().restore(FileReader.readMemento("player",getApplicationContext()));

    }

    //    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.i("Pause", "Call onPause");
//
//        FileWriter.write(game.saveState());
//    }
}
