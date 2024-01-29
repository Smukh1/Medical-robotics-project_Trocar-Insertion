package application;

import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptp;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptpHome;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.lin;

import com.kuka.roboticsAPI.conditionModel.BooleanIOCondition;
import com.kuka.roboticsAPI.conditionModel.IoCondition.*;

import com.kuka.roboticsAPI.conditionModel.ConditionObserver.*;
import com.kuka.roboticsAPI.conditionModel.ObserverManager;
import com.kuka.roboticsAPI.sensorModel.IDataRecorder;
import com.kuka.roboticsAPI.sensorModel.RecorderConfiguration;
import com.kuka.device.common.JointPosition;
import com.kuka.geometry.*;
import com.kuka.geometry.Frame;
import com.kuka.io.IIoDefinition;
import com.kuka.math.geometry.Transformation;
import com.kuka.med.devicemodel.LBRMed;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;
import com.kuka.roboticsAPI.uiModel.IApplicationUI;
import com.kuka.scenegraph.ISceneGraph;
import com.kuka.task.ITaskLogger;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

public class MainTrocarTask extends RoboticsAPIApplication {
  @Inject private LBRMed _lbr;
  @Inject private ISceneGraph sceneGraph;
  @Inject private ITaskLogger logger;
  @Inject private IApplicationUI applicationUi;
  @Inject private World world;
  @Inject private IDataRecorder recorder;

 

  private static final String INFORMATION_TEXT =
      "This application is intended for floor mounted robots!\n\n"
          + "After moving to the home position, the robot moves to the transportation position.";
 

  @Override
  public void initialize() throws Exception {
    
    logger = getLogger();
        
    sceneGraph.clean();
    

    // TODO Initialize your application here
  }

  
  @Override
  public void run() throws Exception {
    //JointPosition actPos = _lbr.getCurrentJointPosition();
    int waiter = 20; // Initializing the wait time
    
     Frame frame = _lbr.getCurrentCartesianPosition(_lbr.getFlange());
    
    Transformation trafo = frame.calculateTransformationFromTreeRoot();
    
    logger.info(" Frame: " +  frame );
    
    // Defining the Recorder configuration
    RecorderConfiguration configuration = recorder
        .getConfigurator()
        .setFileName("Recording_Sujay_Task1_Trial1.log")
        .recordCartesianForce(_lbr.findFrame(
            "/Flange"), frame)

        .buildConfiguration();

    recorder.enable(configuration); // Recorder Enabled
    

 // Taking to the gripper to tool changing position from the current position.   
    Frame tool_change = world.findFrame("ToolChanging");
    
    logger.info(" Trocar Changing: " +  tool_change );
 
    _lbr.getFlange().move(ptp(tool_change));
    logger.info(" Trocar Changing: " +  tool_change );
    

    
    logger.info(" Wait 20 seconds: " );

    TimeUnit.SECONDS.sleep(waiter); // Waiting 20 secs for trocar installation
    

    recorder.startRecording(configuration); // Recording started

    logger.info(" Proceding with 1st Trocar " );
    
    // Approaching to 'Safe' position  
    Frame safe = world.findFrame("Safe");
    logger.info(" New Frame: " +  safe );

    _lbr.getFlange().move(ptp(safe));
    
    logger.info(" safe: " +  safe );
    
 // Approaching to 'Approach1' position
    Frame approach = world.findFrame("Approach1");

    _lbr.getFlange().move(ptp(approach));
    
    logger.info(" Approach: " +  approach );
    
    
 // Approaching to 'Insert1' position with screwing motion
    Frame insert = world.findFrame("Approach1/A1Insert1");

    _lbr.getFlange().move(ptp(insert).setJointVelocityRel(0.1));
    
    logger.info(" insert: " +  insert );
    
    
 // Approaching to 'Insert2' position with screwing motion    
    Frame insert2 = world.findFrame("Approach1/A1Insert2");

    _lbr.getFlange().move(ptp(insert2).setJointVelocityRel(0.1));
    
    logger.info(" insert: " +  insert2 );
    
    
// Approaching to 'Insert3' position with screwing motion
    Frame insert3 = world.findFrame("Approach1/A1Insert3");

    _lbr.getFlange().move(ptp(insert3).setJointVelocityRel(0.1));
    
    logger.info(" insert: " +  insert3 );

    
// Approaching to 'Insert4' position with screwing motion
    Frame insert4 = world.findFrame("Approach1/A1Insert4");

    _lbr.getFlange().move(ptp(insert4).setJointVelocityRel(0.1));
    
    logger.info(" insert: " +  insert4 );
    
    logger.info(" Wait 10 seconds: " );
    
    recorder.stopRecording(); // Recording Stopped


    TimeUnit.SECONDS.sleep(waiter); // Waiting 10 secs for releasing trocar
    
    logger.info(" 1st Trocar Installed in Place " ); //1st trocar installed
    
 // Retracting slowly to approach position 
    Frame approach1 = world.findFrame("Approach1");

    _lbr.getFlange().move(ptp(approach1).setJointVelocityRel(0.2));
    
    logger.info(" Approach: " +  approach1 );
    
 // Retracting to safe position   
    Frame safe2 = world.findFrame("Safe");  

    _lbr.getFlange().move(ptp(safe2));
    
    logger.info(" safe: " +  safe2 );

  }
}