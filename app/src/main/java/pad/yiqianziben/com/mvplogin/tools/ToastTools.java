package pad.yiqianziben.com.mvplogin.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Toastͳһ������
 * 
 */
public class ToastTools {
	private static Toast mToast = null;

	private ToastTools() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean isShow = true;

	/**
	 * ��ʱ����ʾToast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, CharSequence message) {
		if (isShow) {
			if (null != mToast) {
				mToast.cancel();
			}
			mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			mToast.show();
		}
	}

	/**
	 * ��ʱ����ʾToast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, int message) {
		if (isShow) {
			if (null != mToast) {
				mToast.cancel();
			}
			mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			mToast.show();
		}
	}

	/**
	 * ��ʱ����ʾToast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, CharSequence message) {
		if (isShow) {
			if (null != mToast) {
				mToast.cancel();
			}
			mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
			mToast.show();
		}
	}

	/**
	 * ��ʱ����ʾToast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, int message) {
		if (isShow) {
			if (null != mToast) {
				mToast.cancel();
			}
			mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
			mToast.show();
		}
	}

	/**
	 * �Զ�����ʾToastʱ��
	 *  @param context
	 * @param message
	 */
	public static void show(Context context, CharSequence message) {
		if (isShow) {
			if (null != mToast) {
				mToast.cancel();
			}
			mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			mToast.show();
		}
	}

	/**
	 * �Զ�����ʾToastʱ��
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, int message, int duration) {
		if (isShow) {
			if (null != mToast) {
				mToast.cancel();
			}
			mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			mToast.show();
		}
	}

}
