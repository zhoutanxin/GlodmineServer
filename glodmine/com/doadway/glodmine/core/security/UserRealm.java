/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.doadway.glodmine.core.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import com.doadway.glodmine.core.biz.MemberBiz;
import com.doadway.glodmine.core.model.Member;
import com.google.common.base.Objects;
@Service
public class UserRealm extends AuthorizingRealm {
	@Resource
	protected MemberBiz memberBiz;

	public MemberBiz getMemberBiz() {
		return memberBiz;
	}

	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}

	/**
	 * 认证回调函数,登录时调用.
	 */
	
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		/* 这里编写认证代码 */
		try{
			UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
			Member  user = memberBiz.findByMobile(token.getUsername());
			return new SimpleAuthenticationInfo(new ShiroUser(user.getId(),user.getMobilePhone(),user.getPassword()),
				user.getPassword(), user.getMobilePhone());			
		}catch(NullPointerException e){
			return null;
		}

	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/* 这里编写授权代码 */
		Set<String> roleNames = new HashSet<String>();
	    Set<String> permissions = new HashSet<String>();
	    roleNames.add("member");
	    permissions.add("login.do?main");
	    permissions.add("login.do?logout");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
	    info.setStringPermissions(permissions);
		return info;
	}


	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public Integer id;
		public String mobliephone;
		public String niceName;

		public ShiroUser(Integer id, String mobliephone, String niceName) {
			this.id = id;
			this.mobliephone = mobliephone;
			this.niceName = niceName;
		}


		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		
		public String toString() {
			return niceName;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		
		public int hashCode() {
			return Objects.hashCode(mobliephone);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (mobliephone == null) {
				if (other.mobliephone != null) {
					return false;
				}
			} else if (!mobliephone.equals(other.mobliephone)) {
				return false;
			}
			return true;
		}
	}
}
