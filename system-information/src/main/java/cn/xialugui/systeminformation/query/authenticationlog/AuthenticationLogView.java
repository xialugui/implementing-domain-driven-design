/*
 * Copyright (c) 2010-2012. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.xialugui.systeminformation.query.authenticationlog;

import cn.xialugui.sharedkernel.domain.model.user.valueobject.Username;
import cn.xialugui.sharedkernel.infrastructure.persistence.AbstractAuditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class AuthenticationLogView extends AbstractAuditable<String, Long> {
    private String name;
    private Long timestamp;
    private String detail;
}
