package hackustry;

import mindustry.content.Blocks;
import mindustry.ctype.UnlockableContent;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.ContinuousLaserBulletType;
import mindustry.mod.Mod;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.LaserTurret;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.blocks.sandbox.PowerSource;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.meta.BuildVisibility;

import static mindustry.Vars.*;

public class Hackustry extends Mod{

    @Override
    public void init(){
        enableConsole = true;
        content.each(e -> {
            if(e instanceof UnlockableContent){ // 3000iq solution
                ((UnlockableContent)e).alwaysUnlocked = true;
            }
            if(e.minfo.mod == null && e instanceof BulletType){
                BulletType b = (BulletType)e;
                b.collidesAir = true;
                b.collidesGround = true;
            }
        });

        for(Block b : content.blocks()){
            if(b.minfo.mod == null){
                if(b instanceof Turret){
                    Turret e = (Turret)b;
                    e.reloadTime = 0;
                    e.spread = 0;
                    e.inaccuracy = 0;
                    e.recoilAmount = 0;
                    e.restitution = 0;
                    e.xRand = 0;
                    e.cooldown = 10;
                    e.rotateSpeed = Float.MAX_VALUE;
                    e.targetAir = true;
                    e.targetGround = true;
                    if(e == Blocks.lancer){
                        e.chargeTime = 144;
                        e.chargeMaxDelay = 0;
                    }else if(e == Blocks.meltdown){
                        e.range = 999;
                        ((LaserTurret)e).shootDuration = 999;
                        ((ContinuousLaserBulletType)((LaserTurret)e).shootType).length = 999;
                    }
                }else if(b instanceof Reconstructor){
                    b.consumes.items();
                    ((Reconstructor)b).constructTime = 0;
                }else if(b instanceof CoreBlock){
                    b.unitCapModifier = 99999;
                }
                if(b.buildVisibility != BuildVisibility.shown && b.buildVisibility != BuildVisibility.hidden){
                    b.buildVisibility = BuildVisibility.shown;
                }
            }
        }
        ((PowerSource)Blocks.powerSource).powerProduction = Float.MAX_VALUE;
    }
}