// Kyle Peterson, INFO 498, QuizDroid
// Intro to the physics activity
package quizdroid.kylep9.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class PhysicsIntroActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics_intro);

        String[] a1 = {"Wires and cables", "the shock", "Electrons and protons", "I do not know"};
        Question q1 = new Question("What is electricity?", a1, 2);
        String[] a2 = {"gravity man", "42", "apple man", "yes"};
        Question q2 = new Question("Who is newton", a2, 0);
        final Question[] questions = {q1, q2};
        Button begin = (Button) findViewById(R.id.phybegin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextActivity = new Intent(PhysicsIntroActivity.this, MathQuestion1.class);

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
        getMenuInflater().inflate(R.menu.menu_physics_intro, menu);
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
