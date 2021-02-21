package abstractFactory.pattern;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

/**
 * Abstract Factory for the Editor products
 */
public abstract class EditorFactory {
	
	static private EditorFactoryRegistry holder = new EditorFactoryRegistry();
	
	public static EditorFactory createEditorFactoryInstance(Class clazz) {

		if (!holder.isRegistered(clazz))
			construct(clazz);

		return holder.lookup();

	}
	
	private static void construct(Class clazz)  {

		Constructor constructor;
		try {
			constructor = clazz.getDeclaredConstructor();
			EditorFactory instance = (EditorFactory) constructor.newInstance();
			holder.register(instance);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

	}
	
	public abstract JFrame createFrame();

	public abstract JDesktopPane createDesktopPane();

	public abstract JMenuBar createMenuBar(ActionListener listener);

	public abstract JToolBar createToolBar(ActionListener listener);

	public abstract WindowAdapter createWindowAdapter(JDesktopPane pane);

	public abstract ActionListener createActionListener(JDesktopPane pane);
}