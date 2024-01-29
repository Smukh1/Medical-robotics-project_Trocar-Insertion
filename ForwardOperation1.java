package application;

import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptp;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptpHome;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.lin;


import com.kuka.device.common.JointPosition;
import com.kuka.geometry.*;
import com.kuka.geometry.Frame;
import com.kuka.math.geometry.Transformation;
import com.kuka.med.devicemodel.LBRMed;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;
import com.kuka.roboticsAPI.uiModel.IApplicationUI;
import com.kuka.scenegraph.ISceneGraph;
import com.kuka.task.ITaskLogger;
import javax.inject.Inject;

public class ForwardOperation1 extends RoboticsAPIApplication {
  @Inject private LBRMed _lbr;
  @Inject private ISceneGraph sceneGraph;
  @Inject private ITaskLogger logger;
  @Inject private IApplicationUI applicationUi;
  @Inject private World world;

  
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
    
    Frame frame = _lbr.getCurrentCartesianPosition(_lbr.getFlange());
    
    Transformation trafo = frame.calculateTransformationFromTreeRoot();
    
    logger.info(" Frame: " +  frame );
    
    //// Frame parent = _lbr.findFrame("/Base");
    ////logger.info(" P Frame: " +  parent );

    
    //Frame start = world.findFrame("Start"); // created a new tool change application
    ////frame.transform(frame,Transformation.of(50, 0, 0));
    
    //logger.info(" New Frame: " +  start );

   //// _lbr.getFlange().move(ptp(frame));
 
  
    //_lbr.getFlange().move(ptp(start));
    //logger.info(" Start: " +  start );

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

 
 // Not using 'approach2' and 'safe2', made another application for this   
//    Frame approach2 = world.findFrame("Approach");
//
//    _lbr.getFlange().move(ptp(approach2));
//    
//    logger.info(" Approach: " +  approach2 );
//    
//    
//    Frame safe2 = world.findFrame("Safe");
//
//    _lbr.getFlange().move(ptp(safe2));
//    
//    logger.info(" safe: " +  safe2 );
    
 
    
//// Addition part. Not used in the present code (some tests)    
    //Frame parent = object.findFrame("/Base");
    //frame.transform(frame,Transformation.of(0, 20, 0));
    
    //_lbr.getFlange().move(lin(frame).setCartVelocity(40.0));
    
    //frame.transform(frame,Transformation.of(0, -20, 10));
    
    //_lbr.getFlange().move(lin(frame).setCartVelocity(40.0));
    
  }
}