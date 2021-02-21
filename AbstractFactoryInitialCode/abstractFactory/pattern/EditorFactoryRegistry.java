package abstractFactory.pattern;

import java.util.Hashtable;

public class EditorFactoryRegistry {
	private EditorFactory holder;

	public EditorFactoryRegistry() {
		super();
		holder = null;
	}

	public void register(EditorFactory singleton) {
		holder = singleton;
	}

	public EditorFactory lookup() {
		return holder;
	}


	public boolean isRegistered(Class clazz) {
		return null != holder;
	}

	public boolean isRegistered(EditorFactory singleton) {
		return isRegistered(singleton.getClass());
	}
}