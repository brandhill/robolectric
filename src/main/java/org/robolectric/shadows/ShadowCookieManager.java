package org.robolectric.shadows;

import android.webkit.CookieManager;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

import java.util.HashMap;
import java.util.Map;

/**
 * Shadows the {@code android.webkit.CookieManager} class.
 */
@SuppressWarnings({"UnusedDeclaration"})
@Implements(CookieManager.class)
public class ShadowCookieManager {
  private static CookieManager sRef;
  private Map<String,String> cookies = new HashMap<String, String>();
  private boolean accept;

  @Implementation
  public static CookieManager getInstance() {
    if (sRef == null) {
      sRef = Robolectric.newInstanceOf(CookieManager.class);
    }
    return sRef;
  }

  @Implementation
  public void setCookie(String url, String value) {
    cookies.put(url, value);
  }

  @Implementation
  public String getCookie(String url) {
    return cookies.get(url);
  }

  @Implementation
  public void setAcceptCookie(boolean accept) {
    this.accept = accept;
  }

  @Implementation
  public boolean acceptCookie() {
    return this.accept;
  }

  @Implementation
  public void removeAllCookie() {
    cookies.clear();
  }
}
