package polyorbis.entities.instances;

import flounder.entities.*;
import flounder.entities.components.*;
import flounder.lights.*;
import flounder.maths.*;
import flounder.maths.vectors.*;
import flounder.models.*;
import flounder.resources.*;
import flounder.space.*;
import flounder.textures.*;
import polyorbis.entities.components.*;

public class InstanceSpawn extends Entity {
	private static final ModelObject MODEL = ModelFactory.newBuilder().setFile(new MyFile(FlounderEntities.ENTITIES_FOLDER, "spawn", "model.obj")).create();
	private static final TextureObject TEXTURE = TextureFactory.newBuilder().setFile(new MyFile(FlounderEntities.ENTITIES_FOLDER, "spawn", "diffuse.png")).setNumberOfRows(1).create();

	public InstanceSpawn(ISpatialStructure<Entity> structure, Vector3f position, Vector3f rotation) {
		super(structure, position, rotation);
		new ComponentSpawn(this);
		new ComponentModel(this, 0.18f, MODEL, TEXTURE, 1);
		new ComponentSurface(this, 1.0f, 0.0f, false, false);
		new ComponentLight(this, new Vector3f(0.0f, 0.2f, 0.0f), new Colour(0.243f, 0.812f, 0.631f), new Attenuation(1.0f, 0.02f, 2.0f));
		new ComponentCollision(this);
	}
}

