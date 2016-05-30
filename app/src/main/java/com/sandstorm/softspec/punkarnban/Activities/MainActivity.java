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
import com.sandstorm.softspec.punkarnban.Models.Works.Homework;
import com.sandstorm.softspec.punkarnban.Models.Works.Project;
import com.sandstorm.softspec.punkarnban.Models.Works.Work;
import com.sandstorm.softspec.punkarnban.R;
import com.sandstorm.softspec.punkarnban.Graphic.DamagePool;
import com.sandstorm.softspec.punkarnban.Graphic.HealthBar;
import com.sandstorm.softspec.punkarnban.State.DefualtState;
import com.sandstorm.softspec.punkarnban.State.TapState;
import com.sandstorm.softspec.punkarnban.Utility.DecimalConverter;

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
     * Layout for set background
     */
    private RelativeLayout background;

    /**
     * State of tap image
     */
    private TapState state;
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
        background = (RelativeLayout) findViewById(R.id.relative);

        tap = (TapImage) findViewById(R.id.tap); //get image from view

        state = new DefualtState(this,tap);

        final DamagePool damagePool = new DamagePool(); // damage pool (graphic reason)


        Thread t = new Thread(damagePool); //create a thread

        t.start();//start a thread

        tap.setOnClickListener(new View.OnClickListener() {// set onclick listener for image
            @Override
            public void onClick(View v) {
                changeImage();//change image when tap
                int damage = game.tap();//invoke game tap and return the damage
                damagePool.addPool(getApplicationContext(),background,damage);//add into damagePool

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
        game.initWork();
        healthBar.setNameText(game.getWork().getName());
        healthBar.setHealthText("0/" + game.getWork().getHp());
        healthBar.setMax(game.getWork().getHp());

        knowledgePoint = (TextView) findViewById(R.id.player_knowledge);

        level = (TextView) findViewById(R.id.game_level);
        level.setText(game.getPresentLevel() + "");


        setBackground();

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
            Log.i("Timer", "Timing");
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
                    DecimalConverter converter = new DecimalConverter(player.getKnowledge());
                    knowledgePoint.setText(converter.convert());
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

    public void setState(TapState state) {
        this.state = state;
    }

    public MainActivity getActivity() {
        return this;
    }

    public ImageView getTapImage() {
        return tap;
    }
}
