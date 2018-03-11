package com.example.ash.smartamravati.activity.user.dashboard.AmravatiCity;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ash.smartamravati.R;
public class Tourist_Location extends AppCompatActivity {

    TextView text1,text2,text3,text4,text5,text6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist__location);

        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);
        text4 = (TextView)findViewById(R.id.text4);
        text5 = (TextView)findViewById(R.id.text5);
        text6 = (TextView)findViewById(R.id.text6);


        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog("Wadali Lake","Wadali Talao is a reservoir built in the Chandur rail road which is just 3 km away from Amravati city. This place offers scenic beauty, photographic location, boating experience, visit to nearby zoo and a garden. It is an ideal weekend destination to organize picnics, birthday parties or family visits. This place does not offer anything unique or different. So being here for a person who is an outsider wouldn’t be a great option .Hotels available in Amravati are Mehfil, Samrat and Ramgiri hotel. But place surely provides time to relax and enjoy peace of mind by being away from the city life. Enjoy being close to nature.","cancelMethod1");
            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog("Shri Bhakti Dham Temple","Shri Bhakti Dham Temple is located on Badnera road in Amravati and is devoted to Lord Krishna and his consort, Radha. Besides the idols Krishna and Radha, an idol of Shri Sant Jalaram Bappa is also enshrined in the sanctum.\n" +
                        "\n" +
                        "A small and beautiful park is maintained behind the temple premises and is popular among children. Locals and visitors can hire tongas, taxis, buses and auto-rickshaws to visit this religious place.","cancelMethod1");
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog("Satidham Mandir","Satidham Temple is situated in the heart of the city of Amravati at Rallies Plot. The temple comprises of the beautiful idols of Lord Krishna-Radha, Lord Ram and Goddess Sita, Lord Ganesh, Lord Shiva and Rani Satiji placed within the temple. Every year a fair is arranged on the occasion of Janmashtami, which a large number of devotees congregate here to be a part of the festivities.\n" +
                        "\n","cancelMethod1");
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog("Chikhaldara","Chikhaldara is a Hill Station and a municipal council in Amravati district in the indian state of Maharashtra\n" +
                        "\n" +
                        "Featured in the epic of the Mahabharata, this is the place where Bheema killed the villainous Keechaka in a herculean bout and then threw him into the valley. It thus came to be known as Keechakadara—Chikhaldara is its corruption.\n" +
                        "\n" +
                        "But there's more to Chikhaldara. The sole hill resort in the Vidarbha region, it is situated at an altitude of 1118 m with highest vairat point 1188m and has the added dimension of being the only coffee-growing area in Maharashtra. Chikhaldara has an annual rainfall of 154cm. Temperature varies from 39C in summer to 5C in winter. Best months to visit are from October to June.\n" +
                        "\n" +
                        "It abounds in wildlife—Tiger, panthers, sloth bears, sambar, wild boar, and even the rarely seen wild dogs. Close by is the famous Melghat Tiger Project which has 82 tigers.\n" +
                        "\n" +
                        "The scenic beauty of Chikhaldara can be enjoyed from Hurricane Point, Prospect Point, and Devi Point. Other interesting excursions include Gavilgad and Narnala Fort, the Pandit Nehru Botanical Gardens, the Tribal Museum and the Semadoh Lake.","cancelMethod1");
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog("Chatri Lake","Chatri Talao was built in 1888 and is a reservoir, which supplies drinking water to the residents of Amravati city. Situated on the Malkhed Railway Road, 1 km from Dasturnagar Square, the reservoir has its base built on a small spring named Kali Nadi. This talao is visited by tourists in large number due to the small garden and boating facility available here. \n\n Distance : 3.7 KM from RajKamal Square.", "cancelMethod1");
            }
        });

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog("Ambadevi Temple","\n Shri Ambadevi Temple is situated at the heart of the city at Gandhi Square. It is a very old temple and can find it's mention in the old gazetteers. People from all walk of life and from different parts of India visit this temple. The Navaratri Festival , which falls just before Dasara festival, is celebrated by people & the temple authorities with gaiety & Harmony. During these nine days various cultural and religious programs are arranged. Big mela is held on this occasion which is visited by people from all walk of life with same enthusiasm. Ample number of good staying facilities are available in various Hotels nearby. \n\n How to reach - \n It is 1 Km towards West from Amravati Railway station \n 1.5 Km. from Amravati Bus Stand. \n Ample number of local vehicles and taxis are available from Amravati Railway station & Bus Stand.\n\n  The Vehicles and Taxies are also available from Badnera railway station (on Mumbai - Calcutta Rail Route).\n\n Rukmini, the daughter of King Bhishmak of Vidarbha, hears tales of Krishna's courage. She falls in love with him.", "cancelMethod1");
            }
        });
    }




    private void customDialog(String title, String message, final String cancelMethod) {

        final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(this);
        builderSingle.setIcon(R.drawable.ic_bookmark_black);
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);

        builderSingle.setNegativeButton(
                "Back",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(cancelMethod.equals("cancelMethod1")){
                            cancelMethod1();
                        }
                        else if(cancelMethod.equals("cancelMethod2")){
                            cancelMethod2();
                        }

                    }
                });

        builderSingle.show();
    }

    private void cancelMethod1() {

        toastMessage("Back.");
    }

    private void cancelMethod2() {

        toastMessage("Cancel.");
    }

    public void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
