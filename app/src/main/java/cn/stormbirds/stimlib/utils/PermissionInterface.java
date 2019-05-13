package cn.stormbirds.stimlib.utils;

/**
  * <p>Copyright (c) 小宝 @ 2019</p>
  *
  * <p>@ Package Name:    cn.stormbirds.stimlib.utils</p>
  * <p>@ Author：         stormbirds</p>
  * <p>@ Email：          xbaojun@gmail.com</p>
  * <p>@ Created At：     2019/5/13 11:51</p>
  * <p>@ Description：    权限申请接口</p>
  *
  */
public interface PermissionInterface {

/**
 * 可设置请求权限请求码
 */
int getPermissionsRequestCode();

/**
 * 设置需要请求的权限
 */
String[] getPermissions();

/**
 * 请求权限成功回调
 */
void requestPermissionsSuccess();

/**
 * 请求权限失败回调
 */
void requestPermissionsFail();

}