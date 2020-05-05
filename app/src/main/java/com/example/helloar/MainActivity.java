package com.example.helloar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {

    private ArFragment arFragment;
    AnchorNode anchorNode;

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

        Button Desk = findViewById(R.id.Desk);
        Button Chair = findViewById(R.id.Chair);
        Button Clear = findViewById(R.id.Clear);
        Button Bed = findViewById(R.id.Bed);
        Button Bookshelf = findViewById(R.id.Bookshelf);
        Button Couch = findViewById(R.id.Couch);
        Button Fluffy_Chair = findViewById(R.id.Fluffy_Chair);
        Button Long_Couch = findViewById(R.id.Long_Couch);
        Button TV_Table = findViewById(R.id.TV_Table);

        //Menu Hooks
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);

        //Navigation Drawer
        navigationView.bringToFront();


        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            //Anchor anchor1 = hitResult.createAnchor();
            // Anchor anchor2 = hitResult.createAnchor();
            //Anchor anchor3=hitResult.createAnchor();
            // Anchor anchor4=hitResult.createAnchor();
            if (models == Models.DESK)
                placeDesk(hitResult.createAnchor());
            else if (models == Models.CHAIR)
                placeChair(hitResult.createAnchor());
            else if (models == Models.BED)
                placeBed(hitResult.createAnchor());
            else if (models == Models.BOOKSHELF)
                placeBookshelf(hitResult.createAnchor());
            else if (models == Models.COUCH)
                placeCouch(hitResult.createAnchor());
            else if (models == Models.FLUFFY_CHAIR)
                placeFluffyChair(hitResult.createAnchor());
            else if (models == Models.LONG_COUCH)
                placeLongCouch(hitResult.createAnchor());
            else if (models == Models.TV_TABLE)
                placeTVTable(hitResult.createAnchor());
        });

        Desk.setOnClickListener(view -> models = Models.DESK);
        Chair.setOnClickListener(view -> models = Models.CHAIR);
        Bed.setOnClickListener(view -> models = Models.BED);
        Bookshelf.setOnClickListener(view -> models = Models.BOOKSHELF);
        Couch.setOnClickListener(view -> models = Models.COUCH);
        Fluffy_Chair.setOnClickListener(view -> models = Models.FLUFFY_CHAIR);
        Long_Couch.setOnClickListener(view -> models = Models.LONG_COUCH);
        TV_Table.setOnClickListener(view -> models = Models.TV_TABLE);
        Clear.setOnClickListener(view -> removeAnchorNode());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> removeAnchorNode());
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
