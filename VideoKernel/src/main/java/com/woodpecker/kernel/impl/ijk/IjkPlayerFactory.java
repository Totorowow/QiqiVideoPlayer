/*
Copyright 2017 yangchong211（github.com/yangchong211）

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.woodpecker.kernel.impl.ijk;
import android.content.Context;
import com.woodpecker.kernel.factory.PlayerFactory;


/**
 * <pre>
 *     @author yangchong
 *     blog  : https://github.com/yangchong211
 *     time  : 2018/11/9
 *     desc  : ijk视频播放器Factory
 *     revise: 抽象工厂具体实现类
 * </pre>
 */
public class IjkPlayerFactory extends PlayerFactory<IjkVideoPlayer> {

    public static IjkPlayerFactory create() {
        return new IjkPlayerFactory();
    }

    @Override
    public IjkVideoPlayer createPlayer(Context context) {
        return new IjkVideoPlayer(context);
    }
}
