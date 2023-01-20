import java.util.*;

class Main {
  public static void main(String[] args) {
    
    Qtable one = new Qtable(9, 4);
    Random random  = new Random(); 
     
    for(int i=0; i < 100; i++){
      int state  = random.nextInt(4);
      int action = random.nextInt(9);
      int nextState = random.nextInt(4);
      one.updateQTable(state, nextState, action, 0.9); 
      one.print(); 
    }

    

    
  }
}

class Qtable {
            ArrayList<ArrayList<Double>> qValues = new ArrayList<ArrayList<Double>>();
            ArrayList<ArrayList<Double>> Rewards = new ArrayList<ArrayList<Double>>();
            int actions;
            int states;
        
            private final double learningRate = 0.1;
            private final double rewardDecay = 0.9;
            private final double greedy = 0.9;
          
            public Qtable(int actions, int states) {
              this.actions = actions;
              this.states = states; 
          
              initializeQtable(); 
              initializeRewardsMatrix();
          
            }

            public void updateQTable(int currentState, int nextState, int action, double gamma){
                double reward = takeAction(currentState);
                qValues.get(currentState).set(action, qValues.get(currentState).get(action)
                 + learningRate * (reward + (gamma * Collections.max(qValues.get(currentState)) - qValues.get(currentState).get(action))) * 1.0);
            }
          
            public int getAction(){
              Random random = new Random();  
              int actionIndex = random.nextInt(actions); 
            
              return actionIndex; 
            }
          
            public double takeAction(int currentState){
              int action = getAction();  
              double stateActionReward = Rewards.get(currentState).get(action);
              return stateActionReward; 
              
            }
          
            public void initializeQtable(){
              for (int i = 0; i < states; i++) {
                ArrayList<Double> statesColumn = new ArrayList<Double>();
                for (int j = 0; j < actions; j++) {
                  statesColumn.add(0.0);
                }
                qValues.add(statesColumn);
              }
            }
          
            public void initializeRewardsMatrix() {
              //attack or not rewards 
              Rewards.add(new ArrayList<Double>(Arrays.asList(1.0, -0.3, -0.3, -0.3, -0.3, -0.3, -0.3, -0.3, -0.3)));
          
              Rewards.add(new ArrayList<Double>(Arrays.asList(0.3, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5)));
          
              Rewards.add(new ArrayList<Double>(Arrays.asList(-0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7)));
          
              Rewards.add(new ArrayList<Double>(Arrays.asList(-100.0, -100.0, -100.0, -100.0, -100.0, -100.0, -100.0, -100.0, -100.0)));
              
              
            }
          
            public void print() {
              System.out.println(qValues);
            }
          }