package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.graphics.Path;
import android.widget.Toast;

import java.util.UUID;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {
    ImageButton boutonRouge;
    ImageButton boutonViolet;
    ImageButton boutonNoir;
    ImageButton boutonJaune;
    ImageButton boutonTurquoise;
    ImageButton boutonVert;
    ImageButton boutonOrange;
    ImageButton boutonRose;

    ImageView boutonCercle;
    ImageView boutonCrayon;
    ImageView boutonRectangle;
    ImageView boutonTailleTrait;
    ImageView boutonReviensEnArriere;
    ImageView boutonAjouter;
    ImageView boutonTriangle;
    ImageView boutonPotPeinture;
    ImageView boutonEffacer;
    ImageView boutonPipette;
    ImageView boutonEnregistrer;
    LinearLayout barreCouleur;
    ConstraintLayout conteneur;
    Surface s;
    Ecouteur ec;
    ChangeItem changeItem;
    Path path;
    Tracer t;
    Vector<Dessiner> vectorDessiner;
    Vector<Dessiner> vecteurContenantTout;
    Cercle cercle;
    Rectangle rectangle;
    Triangle triangle;
    public int getTailleSpecial() {
        return tailleSpecial;
    }//pour recuperer la taille du crayon quand on veut la modifier

    public void setTailleSpecial(int tailleSpecial) {
        this.tailleSpecial = tailleSpecial;
    } //pour modifier la taille du crayon

    int tailleSpecial = 10;//taille du crayon par defaut
    int couleurEffacer;
    EffacerElementPrecedent effacerElementPrecedent;
    AjouterElement ajouterElement;
    float x, y, xInter, yInter;
    View itemClique;
    View itemCliqueTransition;
    Dessiner d;
    Effaceur e;
    //pour cercle
    float positionDepartEnX;
    float positionDepartEnY;
    //classe pour changement taille crayon
    AlertDialogue boiteDialogue;
    Enregistrer enregistrer;
    Paint cr;
    int couleur;
    int nbrClick = 0;
    boolean pipette = false;
    //pour Pipette
    private Bitmap bitmapImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barreCouleur = findViewById(R.id.barreOutil);
        boutonRouge = findViewById(R.id.redColor);
        boutonViolet = findViewById(R.id.purpleColor);
        boutonNoir = findViewById(R.id.blackColor);
        boutonJaune = findViewById(R.id.yellowColor);
        boutonTurquoise = findViewById(R.id.turquoiseColor);
        boutonVert = findViewById(R.id.greenColor);
        boutonOrange = findViewById(R.id.orangeColor);
        boutonRose = findViewById(R.id.pinkColor);
        conteneur = findViewById(R.id.conteneur);
        boutonCercle = findViewById(R.id.boutonCercle);
        boutonRectangle = findViewById(R.id.boutonRectangle);
        boutonCrayon = findViewById(R.id.boutonCrayon);
        boutonTailleTrait = findViewById(R.id.boutonTailleTrait);
        boutonReviensEnArriere = findViewById(R.id.boutonEffacer);
        boutonAjouter = findViewById(R.id.boutonAjouter);
        boutonTriangle = findViewById(R.id.boutonTriangle);
        boutonPotPeinture = findViewById(R.id.boutonPotPeinture);
        boutonEffacer = findViewById(R.id.boutonGomme);
        boutonPipette = findViewById(R.id.boutonPipette);
        boutonEnregistrer = findViewById(R.id.boutonEnregistrer);

        vectorDessiner = new Vector<>();
        vecteurContenantTout = new Vector<>();
        path = new Path();
        changeItem = new ChangeItem();
        //boucle pour mettre un ecouteur sur les boutons de couleur
        for (int i = 0; i < barreCouleur.getChildCount(); i++) {
            if (barreCouleur.getChildAt(i) instanceof ImageButton) {
                barreCouleur.getChildAt(i).setOnClickListener(changeItem);
            }
        }

        s = new Surface(this);
        s.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        Ecouteur ec = new Ecouteur();
        //mettre un ecouteur aux ImageView
        boutonCercle.setOnClickListener(changeItem);
        boutonCrayon.setOnClickListener(changeItem);
        boutonRectangle.setOnClickListener(changeItem);
        boutonTailleTrait.setOnClickListener(changeItem);
        boutonReviensEnArriere.setOnClickListener(changeItem);
        boutonAjouter.setOnClickListener(changeItem);
        boutonTriangle.setOnClickListener(changeItem);
        boutonPotPeinture.setOnClickListener(changeItem);
        boutonEffacer.setOnClickListener(changeItem);
        boutonPipette.setOnClickListener(changeItem);
        boutonEnregistrer.setOnClickListener(changeItem);
        s.setOnTouchListener(ec);
        conteneur.addView(s);
    }

    public class ChangeItem implements View.OnClickListener {//ecouteur quand on change Item -> je clique sur bouton cercle...que se passe til?

        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View v) {
            //SI JE CLIQUE SUR UNE COULEUR
            if (v instanceof ImageButton) {
                cr = new Paint();//jai besoin de ce crayon pour differencier les couleurs afin que tout ne soit pas de la meme couleur
                cr = new Paint(Paint.ANTI_ALIAS_FLAG);
                cr.setStyle(Paint.Style.STROKE);
                cr.setStrokeWidth(getTailleSpecial());//getTailleSpecial() est utilise pour recuperer la valeur par defaut de la boite taille crayon
                if (v == boutonRouge) {
                    couleur = Color.parseColor("#FF0000");
                    cr.setColor(couleur);
                } else if (v == boutonViolet) {
                    couleur = Color.parseColor("#7f00ff");
                    cr.setColor(couleur);
                } else if (v == boutonNoir) {
                    couleur = Color.parseColor("#FF000000");
                    cr.setColor(couleur);
                } else if (v == boutonJaune) {
                    couleur = Color.parseColor("#FFFF00");
                    cr.setColor(couleur);
                } else if (v == boutonVert) {
                    couleur = Color.parseColor("#008000");
                    cr.setColor(couleur);
                } else if (v == boutonTurquoise) {
                    couleur = Color.parseColor("#00FFFF");
                    cr.setColor(couleur);
                } else if (v == boutonOrange) {
                    couleur = Color.parseColor("#DA870C");
                    cr.setColor(couleur);
                } else if (v == boutonRose) {
                    couleur = Color.parseColor("#FF00FF");
                    cr.setColor(couleur);
                }
            }
            //SINON SI JE CLIQUE SUR UN ITEM-----------------
            else if (v instanceof ImageView) {//si jappuie sur un item: bouton cercle / rectangle ect...
                itemClique = v;
                if (itemClique == boutonCercle || itemClique == boutonCrayon || itemClique == boutonRectangle || itemClique == boutonTriangle) {
                    itemCliqueTransition = itemClique;//itemCliqueTransition est utiliser lorsque que lon veut changer la taille du crayon
                }

                if (itemClique == boutonReviensEnArriere) {
                    if (d != null) {
                        if(vectorDessiner.size() >= 1) {
                            effacerElementPrecedent = new EffacerElementPrecedent(vectorDessiner);
                            d = effacerElementPrecedent;
                            s.invalidate();
                        }
                    }
                } else if (itemClique == boutonAjouter) {
                    if (d != null) {
                        ajouterElement = new AjouterElement(vecteurContenantTout, vectorDessiner);
                        d = ajouterElement;
                        s.invalidate();
                    }
                } else if (itemClique == boutonTailleTrait) {
                    boiteDialogue = new AlertDialogue(MainActivity.this);
                    itemClique = itemCliqueTransition;//itemCliqueTransition permet davoir la forme que lutilisateur voulait dessiner juste avant
                    cr = new Paint();
                    cr = new Paint(Paint.ANTI_ALIAS_FLAG);
                    cr.setStyle(Paint.Style.STROKE);
                    boiteDialogue.show();
                    setTailleSpecial(boiteDialogue.getTaille());//modification de la taille du crayon
                    if (couleur == 0) {
                        couleur = Color.parseColor("#FF000000"); //si aucune couleur n'a été choisi on donne la couleur noir par default
                    }
                    cr.setColor(couleur);//avoir la couleur choisi
                    cr.setStrokeWidth(getTailleSpecial());

                } else if (itemClique == boutonEffacer) {//pour effaceur
                    cr = new Paint();
                    cr = new Paint(Paint.ANTI_ALIAS_FLAG);
                    cr.setStyle(Paint.Style.STROKE);
                    cr.setColor(couleurEffacer);

                } else if (itemClique == boutonEnregistrer) {
                    //le code de la classe Enregistrer a ete trouve sur le site: https://nyxot.github.io/Paint_with_Android/
                    enregistrer = new Enregistrer(MainActivity.this, s, getContentResolver());
                }

                if (cr == null) {//le if est utilise ici lorsqu'on ouvre lappli et qu'on ne choisi pas ditem pour avoir un crayon
                    cr = new Paint();
                    cr = new Paint(Paint.ANTI_ALIAS_FLAG);
                    cr.setStyle(Paint.Style.STROKE);
                    cr.setStrokeWidth(getTailleSpecial());
                }
            }
        }
    }

    public class Ecouteur implements View.OnTouchListener {
        @SuppressLint("ResourceAsColor")
        @Override
        public boolean onTouch(View v, MotionEvent event) {//entre ici quand on touche la zone de dessin
            x = event.getX();//je recupere la position en X de ma souris
            y = event.getY();//position Y de ma souris
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (cr == null) {//quand jouvre le programme le crayon est null
                    cr = new Paint();
                    cr = new Paint(Paint.ANTI_ALIAS_FLAG);
                    cr.setStyle(Paint.Style.STROKE);
                    cr.setStrokeWidth(getTailleSpecial());
                } else {
                    cr.setStrokeWidth(getTailleSpecial());
                }
                //Si l'utilisateur a clique sur ajouter ou enlever un element, je remet le crayon par defaut
                if (itemClique == boutonReviensEnArriere || itemClique == boutonAjouter) {
                    itemClique = boutonCrayon;
                }
                //Si l'utilisateur a utilise la pipette, suite a son utilisation je remet le crayon par defaut
                if (pipette) {
                    itemClique = boutonCrayon;
                    pipette = false;
                }
                //POUR CRAYON
                if (itemClique == null || itemClique == boutonCrayon) {// si je ne choisi rien donc je suis avec le crayon
                    itemCliqueTransition = itemClique;
                    t = new Tracer(x, y, cr);
                    t.getPath().moveTo(x, y);
                    d = t;
                }
                //POUR EFFACEUR
                else if (itemClique == boutonEffacer) {
                    e = new Effaceur(x, y, cr);
                    e.getPath().moveTo(x, y);
                    if (couleurEffacer == 0) {
                        couleurEffacer = Color.parseColor("#FFFFFFFF");//si aucune couleur na ete choisie on donne blanc comme couleur par defaut
                    }
                    cr.setColor(couleurEffacer);
                    d = e;
                }
                //POUR CERCLE (! on peut dessiner le cercle des 2 sens: de la gauche vers la droite et inversement)
                else if (itemClique == boutonCercle) {
                    itemCliqueTransition = itemClique;
                    cercle = new Cercle(x, y, cr);
                    positionDepartEnX = event.getX();
                    d = cercle;
                }
                //POUR RECTANGLE
                else if (itemClique == boutonRectangle) {
                    itemCliqueTransition = itemClique;
                    rectangle = new Rectangle(x, y, cr);
                    positionDepartEnX = event.getX();
                    positionDepartEnY = event.getY();
                    d = rectangle;
                }

                //POUR TRIANGLE
                else if (itemClique == boutonTriangle) {
                    itemCliqueTransition = itemClique;
                    nbrClick += 1;
                    if (nbrClick == 1) {
                        triangle = new Triangle(x, y, cr);//move to du 1er sommet + x et y = position de la souris en down
                        d = triangle;
                    } else {
                        //on prend en note le 3e sommet
                        x = event.getX();//point du 3 sommet
                        y = event.getY();
                        triangle.dessineDeuxiemeTrait(xInter, yInter, x, y);//dessine le 2e trait
                        nbrClick = 0;
                        d = triangle;
                    }
                }

                //POUR POT DE PEINTURE
                else if (itemClique == boutonPotPeinture) {
                    //comme dans paint, pour dessiner un nouvel objet apres avoir utilise le pot de peinture, il faudra choisir une nouvelle couleur
                    s.setBackgroundColor(cr.getColor());//on met la couleur a la surface selon la couleur que l'utilisateur a choisi
                    couleurEffacer = cr.getColor();//on donne a la couleur de l'effaceur la meme couleur que le background
                    for (int i = 0; i < vectorDessiner.size(); i++) {
                        if (vectorDessiner.get(i) instanceof Effaceur) { // On cherche uniquement les objets de type Effaceur
                            //modifie la couleur du crayon en lui mettant celle du pot
                            cr.setColor(couleurEffacer);
                            ((Effaceur) vectorDessiner.get(i)).setCrayon(cr);//puis on modifie les effaceurs en leur donnant le crayon que lon va utiliser pour effacer
                        }
                    }
                }

                //pour pipette
                else if (itemClique == boutonPipette) {
                    int xPipette = Float.valueOf(x).intValue();//on recupere la position x de l'endroit ou l'utilisateur a clique et on transtype en int
                    int yPipette = Float.valueOf(y).intValue();// meme chose pour le y
                    couleur = s.getBitmapImage().getPixel(xPipette, yPipette);//on recupere la couleur de l'endroit ou l'utilisateur a clique
                    cr = new Paint();//on cree un nouveau crayon
                    cr = new Paint(Paint.ANTI_ALIAS_FLAG);
                    cr.setStyle(Paint.Style.STROKE);
                    cr.setStrokeWidth(getTailleSpecial());
                    cr.setColor(couleur);//on donne la couleur au crayon
                    pipette = true;
                }
                return true;
            }
            //SI ON BOUGE------------------------------------------------------------------
            else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (itemClique == null || itemClique == boutonCrayon || itemClique == boutonReviensEnArriere) {//j'ai mis itemClique == boutonReviensEnArriere pour pouvoir redessiner avec un crayon par defaut
                    t.getPath().lineTo(x, y);
                    d = t;
                }
                //POUR EFFACER
                else if (itemClique == boutonEffacer) {
                    e.getPath().lineTo(x, y);
                    d = e;
                }
                //POUR CERCLE
                else if (itemClique == boutonCercle) {
                    cercle.redimensionnerCercle(positionDepartEnX, event.getX());//on redimensionne la taille du cercle quand on bouge
                    d = cercle;
                }
                //POUR RECTANGLE
                else if (itemClique == boutonRectangle) {
                    rectangle.redimensionnerRectangle(event.getX(), event.getY());//on redimensionne la taille du rectangle quand on bouge
                    d = rectangle;
                }
                //POUR TRIANGLE
                else if (itemClique == boutonTriangle) {
                    x = event.getX();
                    y = event.getY();
                    triangle.pointArrivePremierTrait(x, y);//pour dessiner le 1er trait
                    xInter = x;//j'ai beosin de xInter et yInter pour dessiner le 2e et 3e trait
                    yInter = y;
                    d = triangle;
                }
                s.invalidate();
                return true;
            }

            //QUAND JE RELACHE LA SOURIS
            else if (event.getAction() == MotionEvent.ACTION_UP) {
                if (d != null) {
                    vectorDessiner.add(d);//j'ajoute ma forme au vecteur
                    vecteurContenantTout.add(d);//je l'ajoute egalement a ce vecteur que j'utilise quand je fais le redo
                    s.invalidate();
                }
            }
            return true;
        }
    }

    public class Surface extends View {
        //pour pipette
        public Bitmap getBitmapImage() {
            this.buildDrawingCache();
            bitmapImage = Bitmap.createBitmap(this.getDrawingCache());
            this.destroyDrawingCache();
            return bitmapImage;
        }

        public Surface(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (d != null) {
                if (vectorDessiner.size() > 0) {//pour dessiner quand je relache la souris
                    for (Dessiner dessiner : vectorDessiner) {
                        dessiner.dessiner(canvas);    //ici je dessine ce quil y a dans le vecteur vectorDessiner
                    }
                }
                d.dessiner(canvas);//permet de dessiner sur le moment present
            }
        }
    }
}