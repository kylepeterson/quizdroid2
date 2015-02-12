// Kyle Peterson, INFO 498, QuizDroid
// Marvel Inroduction Activity
package quizdroid.kylep9.washington.edu.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MarvelIntroActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marvel_intro);

        String[] a1 = {"man with the webs", "wrestler hero", "sports guy", "gunslinger"};
        Question q1 = new Question("Who is Spiderman?", a1, 0);
        String[] a2 = {"apple man", "Ltan See", "drawing man", "i can not say"};
        Question q2 = new Question("Who is Stan Lee", a2, 2);
        String[] a3 = {"Your Tiny Brother", "man with no fear", "The man of iron", "Me"};
        Question q3 = new Question("Who is Tony Stark?", a3, 2);
        String[] a4 = {"My Dad", "Your Dad", "Our Dad", "I dont know my dad"};
        Question q4 = new Question("Who is Professor X?", a4, 1);
        final Question[] questions = {q1, q2, q3, q4};
        Button begin = (Button) findViewById(R.id.marvelbegin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextActivity = new Intent(MarvelIntroActivity.this, MathQuestion1.class);

                nextActivity.putExtra("count", 0);
                nextActivity.putExtra("correct", 0);
                nextActivity.putExtra("questions", questions);
                nextActivity.putExtra("current", 0);

                startActivity(nextActivity);

                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_marvel_intro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
