package com.springboot.CinemaSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.springboot.CinemaSystem.dto.PermissionDto;
import com.springboot.CinemaSystem.dto.RoleDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleID")
	private long ID;
	private String name;

	@OneToMany(mappedBy = "role")
	@JsonIgnore
	private List<User> users = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "role_permission",
			joinColumns = @JoinColumn(name = "roleID"),
			inverseJoinColumns = @JoinColumn(name = "permissionID")
	)
	private List<Permission> permission = new ArrayList<>();

	public Role(long ID) {
		this.ID = ID;
	}

	public RoleDto toRoleDto() {
		return new RoleDto(
				this.getID(),
				this.getName(),
				this.getUsers().size(),
				this.getPermission().stream()
						.map(entry2 -> new PermissionDto(
								entry2.getID(),
								entry2.getName()
						))
						.collect(Collectors.toList())
		);
	}
}