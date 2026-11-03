/*Write a program in solidity to create Student data. Use the following constructs:
 Structures
 Arrays
 Fallback
Deploy this as smart contract on Ethereum and Observe the transaction fee and Gas values.*/



// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

/**
 * @title StudentRegistry
 * @notice Demonstrates Structs, dynamic Arrays, and fallback/receive handlers for gas observation.
 */
contract StudentRegistry {
    // 1. Structure construct
    struct Student {
        uint256 rollNo;
        string name;
        string course;
        uint256 admissionTimestamp;
    }

    // 2. Dynamic Array construct
    Student[] public students;

    // Mapping to enforce unique student roll numbers
    mapping(uint256 => bool) private rollNumberRegistered;

    // Event definitions
    event StudentAdded(uint256 indexed rollNo, string name, string course);
    event FallbackTriggered(address indexed sender, uint256 value, bytes data);
    event ReceiveTriggered(address indexed sender, uint256 value);

    /**
     * @notice Add a new student record to the registry.
     * @param _rollNo Unique roll number.
     * @param _name Student full name.
     * @param _course Enrolled department/course.
     */
    function addStudent(
        uint256 _rollNo, 
        string calldata _name, 
        string calldata _course
    ) external {
        require(!rollNumberRegistered[_rollNo], "Roll number already exists");

        students.push(Student({
            rollNo: _rollNo,
            name: _name,
            course: _course,
            admissionTimestamp: block.timestamp
        }));

        rollNumberRegistered[_rollNo] = true;
        emit StudentAdded(_rollNo, _name, _course);
    }

    /**
     * @notice Returns the total count of students stored.
     */
    function getTotalStudents() external view returns (uint256) {
        return students.length;
    }

    /**
     * @notice Retrieve full student details by array index.
     * @param _index Index in the students array.
     */
    function getStudent(uint256 _index) external view returns (
        uint256 rollNo,
        string memory name,
        string memory course,
        uint256 admissionTimestamp
    ) {
        require(_index < students.length, "Index out of bounds");
        Student storage s = students[_index];
        return (s.rollNo, s.name, s.course, s.admissionTimestamp);
    }

    /**
     * @notice 3. Fallback function: Executed when calldata does not match any function signature.
     */
    fallback() external payable {
        emit FallbackTriggered(msg.sender, msg.value, msg.data);
    }

    /**
     * @notice Receive function: Executed on plain Ether transfers without calldata.
     */
    receive() external payable {
        emit ReceiveTriggered(msg.sender, msg.value);
    }
}