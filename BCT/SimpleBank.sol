//use Remix IDE
/*Write a smart contract on a test network, for Bank account of a customer for following
operations:
 Deposit money
 Withdraw Money
 Show balance*/

// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

/**
 * @title SimpleBank
 * @notice Demonstrates balance tracking, deposits, and secure withdrawals on Ethereum.
 */
contract SimpleBank {
    // Mapping customer Ethereum addresses to their bank balances (in Wei)
    mapping(address => uint256) private balances;

    // Events for logging on-chain transactions
    event Deposited(address indexed account, uint256 amount);
    event Withdrawn(address indexed account, uint256 amount);

    /**
     * @notice Deposit Ether into your bank account.
     * Accessible by sending Wei along with the transaction.
     */
    function deposit() external payable {
        require(msg.value > 0, "Deposit amount must be greater than 0");
        
        balances[msg.sender] += msg.value;
        emit Deposited(msg.sender, msg.value);
    }

    /**
     * @notice Withdraw a specified amount of Ether from your bank account.
     * @param _amount Amount to withdraw in Wei.
     */
    function withdraw(uint256 _amount) external {
        require(_amount > 0, "Withdrawal amount must be greater than 0");
        require(balances[msg.sender] >= _amount, "Insufficient balance");

        // Checks-Effects-Interactions pattern to prevent reentrancy attacks
        balances[msg.sender] -= _amount;

        (bool success, ) = payable(msg.sender).call{value: _amount}("");
        require(success, "Ether transfer failed");

        emit Withdrawn(msg.sender, _amount);
    }

    /**
     * @notice View the current balance of the caller.
     * @return Balance of the calling address in Wei.
     */
    function showBalance() external view returns (uint256) {
        return balances[msg.sender];
    }
}