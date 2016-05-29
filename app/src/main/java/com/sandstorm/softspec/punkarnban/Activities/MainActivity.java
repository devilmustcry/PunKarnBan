package com.sandstorm.softspec.punkarnban.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sandstorm.softspec.punkarnban.Adapters.PagerAdapter;
import com.sandstorm.softspec.punkarnban.Graphic.TapImage;
import com.sandstorm.softspec.punkarnban.Models.Game.Game;
import com.sandstorm.softspec.punkarnban.Models.Player.Player;
import com.sandstorm.softspec.punkarnban.Models.Works.Homework;
import com.sandstorm.softspec.punkarnban.Models.Works.Project;
import com.sandstorm.softspec.punkarnban.Models.Works.Work;
import com.sandstorm.softspec.punkarnban.R;
import com.sandstorm.softspec.punkarnban.Graphic.DamagePool;
import com.sandstorm.softspec.punkarnban.Graphic.HealthBar;

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
     * val use to set the image (will change into more suitable way later)
     */

    private int val = 0;

    /**
     * knowledgePoint show in game
     */
    private TextView knowledgePoint;

    /**
     * Level present in game
     */
    private TextView level;

    /**
     * Create an activity
     * @param savedInstanceState : Don't know what is it....
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

    }

    /**
     * init Component in activity
     */
    private void initComponents() {

        game = Game.getInstance(); //get instance from game
        game.addObserver(this); // add this as an obsever to observe game


        tap = (TapImage) findViewById(R.id.tap); //get image from view

        final DamagePool damagePool = new DamagePool(); // damage pool (graphic reason)
        final RelativeLayout ll = (RelativeLayout)findViewById(R.id.relative); //get relativelayout


        Thread t = new Thread(damagePool); //create a thread

        t.start();//start a thread

        tap.setOnClickListener(new View.OnClickListener() {// set onclick listener for image
            @Override
            public void onClick(View v) {
                changeImage();//change image when tap
                int damage = game.tap();//invoke game tap and return the damage
                damagePool.addPool(getApplicationContext(),ll,damage);//add into damagePool

            }
        });

        //Initialize upgradeTab
        upgradeTab = (TabLayout) findViewById(R.id.upgrade_layout);
        upgradeTab.addTab(upgradeTab.newTab().setText("Shop"));
        upgradeTab.addTab(upgradeTab.newTab().setText("Recruit"));
        upgradeTab.addTab(upgradeTab.newTab().setText("Skill"));
        upgradeTab.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initialize upgradeDetail and adapter
        upgradeDetail = (ViewPager) findViewById(R.id.upgrade_detail);
        adapter = new PagerAdapter(getSupportFragmentManager(), upgradeTab.getTabCount() );
        upgradeDetail.setAdapter(adapter);

        //addOnPageChangeListener and setOnTabSelectedListener
        upgradeDetail.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(upgradeTab));
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
        healthBar = (HealthBar) findViewById(R.id.work_health_bar);
        Work work = game.initWork();
        healthBar.setNameText(work.getName());
        healthBar.setHealthText("0/" + work.getHp());
        healthBar.setMax(work.getHp());

        knowledgePoint = (TextView) findViewById(R.id.player_knowledge);

        level = (TextView) findViewById(R.id.game_level);
        level.setText(game.getPresentLevel()+"");


    }

    /**
     * Change image method
     */
    private void changeImage() {

        if (val % 2 == 0) {
            tap.setImageResource(R.drawable.tap_tapping);
            val = 1;

        } else {
            tap.setImageResource(R.drawable.tap_default);
            val = 2;
        }

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
        }
        else if(data.getClass() == Project.class) {
            Log.i("Timer", "Timing");
            Project work = (Project) data;
            if(!projectSet) {
               setAllText(work);
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
                    knowledgePoint.setText(player.getKnowledge()+"");
                }
            });

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
                level.setText(game.getPresentLevel()+"");
            }
        });
    }
}
