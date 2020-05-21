package com.example.helloar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.Camera;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.SceneView;
import com.google.ar.sceneform.Sun;
import com.google.ar.sceneform.rendering.Material;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import static com.google.ar.schemas.lull.VertexAttributeUsage.Color;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArFragment arFragment;
    int countdesk=0;
    int countarmchair=0;
    int countbed=0;
    int countbookshelf=0;
    int countshortcouch=0;
    AnchorNode anchorNode;
    ImageView menuIcon;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       int id = item.getItemId();

       switch (id){
           case R.id.nav_desk:
               models = Models.DESK;
               Toast.makeText(getApplicationContext(),"desk chosen",Toast.LENGTH_LONG).show();
               break;
           case R.id.nav_armchair:
               models = Models.CHAIR;
               Toast.makeText(getApplicationContext(),"Arm chair chosen",Toast.LENGTH_LONG).show();
               break;
           case R.id.nav_bed:
               models = Models.BED;
               Toast.makeText(getApplicationContext(),"bed chosen",Toast.LENGTH_LONG).show();
               break;
           case R.id.nav_bookshelf:
               models = Models.BOOKSHELF;
               Toast.makeText(getApplicationContext(),"bookshelf chosen",Toast.LENGTH_LONG).show();
               break;
           case R.id.nav_shortcouch:
               models = Models.COUCH;
               Toast.makeText(getApplicationContext(),"Short Couch chosen",Toast.LENGTH_LONG).show();
               break;
           case R.id.nav_fluffychair:
               models = Models.FLUFFY_CHAIR;
               Toast.makeText(getApplicationContext(),"Fluffy chair chosen",Toast.LENGTH_LONG).show();
               break;
           case R.id.nav_longcouch:
               models = Models.LONG_COUCH;
               Toast.makeText(getApplicationContext(),"Long Couch chosen",Toast.LENGTH_LONG).show();
               break;
           case R.id.nav_tvtable:
               models = Models.TV_TABLE;
               Toast.makeText(getApplicationContext(),"TV Table chosen",Toast.LENGTH_LONG).show();
               break;
           case R.id.nav_logout:
               Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
               startActivity(intent);
               finish();
               Toast.makeText(getApplicationContext(),"Signing out",Toast.LENGTH_LONG).show();
               break;
           case R.id.nav_items:
               Intent intent2 = new Intent(getApplicationContext(),Items_selected.class);
               intent2.putExtra("count",countdesk);
               intent2.putExtra("count2",countarmchair);
               intent2.putExtra("count3",countbed);
               intent2.putExtra("count4",countbookshelf);
               intent2.putExtra("count5",countshortcouch);
               startActivity(intent2);
               finish();
               Toast.makeText(getApplicationContext(),"Items placed",Toast.LENGTH_LONG).show();
               break;
           case R.id.nav_story:
               Intent intent3 = new Intent(getApplicationContext(),Getting_started.class);
               startActivity(intent3);
               finish();
               Toast.makeText(getApplicationContext(),"About page",Toast.LENGTH_LONG).show();
               break;
           case R.id.nav_clear:
               removeAnchorNode();
               Toast.makeText(getApplicationContext(),"Items Cleared!",Toast.LENGTH_LONG).show();
               break;


       }
       drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private enum Models {
        DESK,
        CHAIR,
        BED,
        BOOKSHELF,
        COUCH,
        FLUFFY_CHAIR,
        LONG_COUCH,
        TV_TABLE
    }

    private Models models = Models.DESK;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arfragment);
        //Menu Hooks
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        menuIcon=findViewById(R.id.imageButton2);

        //Navigation Drawer
        navigationDrawer();


        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            if (models == Models.DESK) {
                placeDesk(hitResult.createAnchor());
                countdesk++;
            }
            else if (models == Models.CHAIR) {
                placeChair(hitResult.createAnchor());
                countarmchair++;
            }
            else if (models == Models.BED) {
                placeBed(hitResult.createAnchor());
                countbed++;
            }
            else if (models == Models.BOOKSHELF) {
                placeBookshelf(hitResult.createAnchor());
                countbookshelf++;
            }
            else if (models == Models.COUCH) {
                placeCouch(hitResult.createAnchor());
                countshortcouch++;
            }
            else if (models == Models.FLUFFY_CHAIR)
                placeFluffyChair(hitResult.createAnchor());
            else if (models == Models.LONG_COUCH)
                placeLongCouch(hitResult.createAnchor());
            else if (models == Models.TV_TABLE)
                placeTVTable(hitResult.createAnchor());
        });

    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();

    }

    private void placeChair(Anchor anchor) {
        ModelRenderable.builder().setSource(this, Uri.parse("Armchair.sfb")).build().thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable)).exceptionally(throwable -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(throwable.getMessage()).show();
            return null;
        });
    }


    private void placeTVTable(Anchor anchor) {
        ModelRenderable.builder().setSource(this, Uri.parse("TV_Table.sfb")).build().thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable)).exceptionally(throwable -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(throwable.getMessage()).show();
            return null;
        });
    }

    private void placeLongCouch(Anchor anchor) {
        ModelRenderable.builder().setSource(this, Uri.parse("Long_Couch.sfb")).build().thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable)).exceptionally(throwable -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(throwable.getMessage()).show();
            return null;
        });
    }


    private void placeFluffyChair(Anchor anchor) {
        ModelRenderable.builder().setSource(this, Uri.parse("Fluffy_Chair.sfb")).build().thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable)).exceptionally(throwable -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(throwable.getMessage()).show();
            return null;
        });
    }

    private void placeCouch(Anchor anchor) {
        ModelRenderable.builder().setSource(this, Uri.parse("Couch.sfb")).build().thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable)).exceptionally(throwable -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(throwable.getMessage()).show();
            return null;
        });
    }

    private void placeBookshelf(Anchor anchor) {
        ModelRenderable.builder().setSource(this, Uri.parse("Bookshelf.sfb")).build().thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable)).exceptionally(throwable -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(throwable.getMessage()).show();
            return null;
        });
    }

    private void placeBed(Anchor anchor) {
        ModelRenderable.builder().setSource(this, Uri.parse("Bed.sfb")).build().thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable)).exceptionally(throwable -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(throwable.getMessage()).show();
            return null;
        });
    }

    private void placeDesk(Anchor anchor) {
        ModelRenderable.builder().setSource(this, Uri.parse("Desk.sfb")).build().thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable)).exceptionally(throwable -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(throwable.getMessage()).show();
            return null;
        });

    }


    private void removeAnchorNode() {
        //Remove an anchor node
        List<Node> children = new ArrayList<>(arFragment.getArSceneView().getScene().getChildren());
        for (Node node : children) {
            if (node instanceof AnchorNode) {
                if (((AnchorNode) node).getAnchor() != null) {
                    ((AnchorNode) node).getAnchor().detach();
                }
            }
            if (!(node instanceof Camera) && !(node instanceof Sun)) {
                node.setParent(null);
            }
        }
         countdesk=0;
         countarmchair=0;
         countbed=0;
         countbookshelf=0;
         countshortcouch=0;
    }


    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();

    }
}
