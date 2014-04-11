package pfc.game.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pfc.game.domain.AuxGoal;
import pfc.game.domain.Goal;
import pfc.game.domain.Player;
import pfc.game.presentation.R;
import pfc.game.domain.Record;
import pfc.game.persistence.Agent;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ProfilePage extends Activity{
	private Player pla;
	
	private ExpandableListAdapter listAdapter;
	private ExpandableListView expListView;
	private List<String> listDataHeader;
	private HashMap<String, List<AuxGoal>> listDataChild;
	private Agent agent;
	private List<Goal> GoalList;
	private List<Record> RecordList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_page);
		
		pla=getIntent().getParcelableExtra("Player");
		agent=new Agent();
		GoalList=agent.ReadGoalListDB(this);
		RecordList=pla.getRecList();
	}
	@Override
	public void onResume(){
		super.onResume();
		
		TextView name=(TextView)findViewById(R.id.profileName);
		name.setText(pla.getName());
		TextView surname=(TextView)findViewById(R.id.profileSurname);
		surname.setText(pla.getSurname());
		TextView nick=(TextView)findViewById(R.id.profileNick);
		nick.setText(pla.getNick());
		TextView password=(TextView)findViewById(R.id.profilePassword);
		password.setText(pla.getPassword());
		
        expListView = (ExpandableListView) findViewById(R.id.profileGoalsRec);
        prepareListData();
        
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
		expListView.setAdapter(listAdapter);
	}
	private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<AuxGoal>>();
 
        // Adding child data
        listDataHeader.add("Goals");
        listDataHeader.add("Records");
 
        int i;
        AuxGoal aux;
        // Adding child data
        List<AuxGoal> Goals = new ArrayList<AuxGoal>();
        for(i=0;i<GoalList.size();i++){
        	if(pla.getGoalList().contains(GoalList.get(i))){
        		aux=new AuxGoal(GoalList.get(i).getName(),GoalList.get(i).getDescription(),true);
        	}
        	else{
        		aux=new AuxGoal(GoalList.get(i).getName(),GoalList.get(i).getDescription(),false);
        	}
        	Goals.add(aux);
        }
 
        List<AuxGoal> Records = new ArrayList<AuxGoal>();
        for(i=0;i<RecordList.size();i++){
        	aux=new AuxGoal(RecordList.get(i).getName(),Integer.toString(RecordList.get(i).getValue()),false);
        	Records.add(aux);
        }
 
        listDataChild.put(listDataHeader.get(0), Goals); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Records);
    }
}
