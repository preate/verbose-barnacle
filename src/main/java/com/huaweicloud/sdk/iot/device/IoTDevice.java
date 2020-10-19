/*Copyright 2020 Huawei Technologies Co., Ltd
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 * */

package com.huaweicloud.sdk.iot.device;

import android.content.Context;

import com.huaweicloud.sdk.iot.device.client.ClientConf;
import com.huaweicloud.sdk.iot.device.client.DeviceClient;
import com.huaweicloud.sdk.iot.device.service.AbstractDevice;
import com.huaweicloud.sdk.iot.device.service.AbstractService;

import java.security.KeyStore;
import java.util.List;


/**
 * IOT设备类，SDK的入口类，提供两种使用方式：
 * 1、面向物模型编程：根据物模型实现设备服务，SDK自动完成设备和平台之间的通讯。这种方式简单易用，适合大多数场景
 * public class SmokeDetectorService extends AbstractService {
 *
 * @Property int smokeAlarm = 1;
 * <p>
 * public int getSmokeAlarm() {
 * //从设备读取属性
 * }
 * <p>
 * public void setSmokeAlarm(int smokeAlarm) {
 * //向设备写属性
 * }
 * }
 * <p>
 * //创建设备
 * IoTDevice device = new IoTDevice(serverUri, deviceId, secret);
 * //添加服务
 * SmokeDetectorService smokeDetectorService = new SmokeDetectorService();
 * device.addService("smokeDetector", smokeDetectorService);
 * device.init();
 * <p>
 * <p>
 * 2、面向通讯接口编程：获取设备的客户端，直接和平台进行通讯。这种方式更复杂也更灵活
 * <p>
 * IoTDevice device = new IoTDevice(serverUri, deviceId, secret);
 * device.init();
 * device.getClient().reportDeviceMessage(new DeviceMessage("hello"));
 * device.getClient().reportProperties(....)
 */
public class IoTDevice extends AbstractDevice {

    /**
     * 构造函数，使用密码创建设备
     *
     * @param mContext      上下文
     * @param serverUri    平台访问地址，比如ssl://iot-mqtts.cn-north-4.myhuaweicloud.com:8883
     * @param deviceId     设备id
     * @param deviceSecret 设备密码
     */
    public IoTDevice(Context mContext, String serverUri, String deviceId, String deviceSecret) {
        super(mContext, serverUri, deviceId, deviceSecret);

    }

    /**
     * 构造函数，使用证书创建设备
     *
     * @param mContext      上下文
     * @param serverUri   平台访问地址，比如ssl://iot-mqtts.cn-north-4.myhuaweicloud.com:8883
     * @param deviceId    设备id
     * @param keyStore    证书容器
     * @param keyPassword 证书密码
     */
    public IoTDevice(Context mContext, String serverUri, String deviceId, KeyStore keyStore, String keyPassword) {

        super(mContext, serverUri, deviceId, keyStore, keyPassword);
    }

    /**
     * 构造函数，直接使用客户端配置创建设备，一般不推荐这种做法
     *
     * @param mContext      上下文
     * @param clientConf 客户端配置
     */
    public IoTDevice(Context mContext, ClientConf clientConf) {
        super(mContext, clientConf);
    }

    /**
     * 初始化，创建到平台的连接
     *
     */
    public void init() {
        super.init();
    }

    /**
     * 关闭到平台的连接
     *
     */
    public void close() {
        super.close();
    }

    /**
     * 添加服务。用户基于AbstractService定义自己的设备服务，并添加到设备
     *
     * @param serviceId     服务id，要和设备模型定义一致
     * @param deviceService 服务实例
     */
    public void addService(String serviceId, AbstractService deviceService) {

        super.addService(serviceId, deviceService);
    }


    /**
     * 查询服务
     *
     * @param serviceId 服务id
     * @return AbstractService 服务实例
     */
    public AbstractService getService(String serviceId) {

        return super.getService(serviceId);
    }


    /**
     * 触发属性变化，SDK会上报变化的属性
     *
     * @param serviceId  服务id
     * @param properties 属性列表
     */
    public void firePropertiesChanged(String serviceId, String... properties) {
        super.firePropertiesChanged(serviceId, properties);
    }

    /**
     * 触发多个服务的属性变化，SDK自动上报变化的属性到平台
     *
     * @param serviceIds 发生变化的服务id列表
     */
    public void fireServicesChanged(List<String> serviceIds) {
        super.fireServicesChanged(serviceIds);
    }

    /**
     * 获取设备客户端。获取到设备客户端后，可以直接调用客户端提供的消息、属性、命令等接口
     *
     * @return 设备客户端实例
     */
    public DeviceClient getClient() {
        return super.getClient();
    }

}
